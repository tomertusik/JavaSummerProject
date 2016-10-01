package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Observable;
import model.Model;
import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;
import view.MyView;
import view.View;
/**
 * Run the program
 * @author Tomer, Gilad
 *
 */
public class Run {

	public static void main(String[] args) {
		
		View view= null;
		
		Model model = new MyModel();
		
		if(model.getProperties().getViewType().equals("Game")){
			view = new MazeWindow();
		}
		else if(model.getProperties().getViewType().equals("Commands")){
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(System.out);
			view = new MyView(in, out);
		}
		Presenter presenter = new Presenter(model, view);
		((Observable) model).addObserver(presenter);
		((Observable) view).addObserver(presenter);
		
		view.start();
	}

}
