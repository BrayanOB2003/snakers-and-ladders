package model;

public class Board {

	
	private Box first;
	private int numRows;
	private int numColumns;
	
	public Board(int n, int m) {
		numRows = n;
		numColumns = m;
		makeBoard();
	}
	
	
	public void makeBoard() {
		first = new Box(0,0,0);
		createRow(0,0, first);
		searchInitialPosition(numRows, first, 1);
	}



	private void createRow(int i, int j, Box currentFirstRow) {

		createCol(i,j+1, currentFirstRow, currentFirstRow.getUp());
		if(i+1 < numRows) {
			Box downFirstRow = new Box(i+1,j,0);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1, j, downFirstRow);
		}
	}


	private void createCol(int i, int j, Box prev, Box rowPrev) {
		
		if(j < numColumns) {
			Box current = new Box(i,j,0);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev != null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			createCol(i, j+1, current, rowPrev);
		}
	}
	
	
	//-----------------------------------Imprimir matriz---------------------------------------------------
	
	public String toString() {
		String msg;
		msg = toStringRow(first);
		return msg;
	}


	private String toStringRow(Box firstRow) {
		String msg = "";
		if(firstRow != null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}


	private String toStringCol(Box current) {
		String msg = "";
		if(current != null) {
			msg = current.toString();
			msg += toStringCol(current.getNext());
		}
		return msg;
	}

	//---------------------------------------Buscar----------------------------------------------
	private void searchInitialPosition(int n, Box current, int i) {
		if(current.getDown().getRow() == n-1) {
			current.getDown().setBoxNumber(i);
			enumToRigth(current.getDown(), i);
		}else {
			searchInitialPosition(n, current.getDown(), i);
		}
	}

	private void enumToRigth(Box current, int i) {

		if(current.getUp() != null) {
			if(current.getNext() != null) {
				current.getNext().setBoxNumber(i+1);
				enumToRigth(current.getNext(), i+1);
			}else {
				current.getUp().setBoxNumber(i+1);
				enumToLeft(current.getUp(), i+1);
			}
			
		}else {
			enumToRigthWitouUp(current, i);
		}
	}
	
	private void enumToLeft(Box current, int i) {
		
		if(current.getUp() != null) {
			if(current.getPrev() != null) {
				current.getPrev().setBoxNumber(i+1);
				enumToLeft(current.getPrev(), i+1);
			}else {
				current.getUp().setBoxNumber(i+1);
				enumToRigth(current.getUp(), i+1);
			}
			
		}else {
			enumToLeftWithouUp(current, i);
		}
	}


	private void enumToLeftWithouUp(Box current, int i) {
		if(current.getPrev() != null) {
			current.getPrev().setBoxNumber(i+1);
			enumToLeftWithouUp(current.getPrev(), i+1);
		}
	}


	//-------------------------------------Getters and setters ----------------------------------------------
	
	private void enumToRigthWitouUp(Box current, int i) {
		if(current.getNext() != null) {
			current.getNext().setBoxNumber(i+1);
			enumToRigthWitouUp(current.getNext(), i+1);
		}
	}


	public int getNumColumns() {
		return numColumns;
	}


	public int getNumRows() {
		return numRows;
	}


	public Box getFirst() {
		return first;
	}


	public void setFirst(Box first) {
		this.first = first;
	}
}
