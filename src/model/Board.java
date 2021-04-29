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


	//-------------------------------------Getters and setters ----------------------------------------------
	
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
