package Controller;

import Modelo.Model;


public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
    }
    
}
