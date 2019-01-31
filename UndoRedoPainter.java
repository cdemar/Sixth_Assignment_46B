package stacklab;

import java.util.*;

public class UndoRedoPainter extends Painter {
	// Called when the user pushes the Undo button.
	void undo() {
		if (getHistory().isEmpty() == false) {
			Circle cc = getHistory().pop();
			getUndoHistory().push(cc);
			repaint();
			// added from Sabrianne
			setRedoButtonEnabled(true);
		} else {
			return;
		}
	}

	// Called when the user pushes the Redo button.
	void redo() {
		if (getUndoHistory().isEmpty() == false) {
			Circle cc = getUndoHistory().pop();
			getHistory().push(cc);
			repaint();
		} else {
			return;
		}
	}

	public static void main(String[] args) {
		UndoRedoPainter p = new UndoRedoPainter();
		p.setVisible(true);

		p.setUndoButtonEnabled(false);
		p.setRedoButtonEnabled(false);

		if (!p.getHistory().isEmpty()) {
			p.setUndoButtonEnabled(true);
		}
		if (!p.getUndoHistory().isEmpty()) {
			p.setRedoButtonEnabled(true);
		}

		/*
		 * if (p.getUndoHistory() != null) {
		 * 	 p.setRedoButtonEnabled(true); }
		 * 
		 * if (p.getUndoHistory() == null) {
		 * 	 p.setRedoButtonEnabled(false); } if
		 * (p.getHistory() == null) { 
		 * 	p.setUndoButtonEnabled(false); }
		 */
	}
}
