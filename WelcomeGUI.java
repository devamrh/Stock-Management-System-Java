import gui.admin.AdminGUI;
import gui.admin.ImportedProductView;
import gui.admin.RegularProductView;
import gui.admin.DiscountProductView;
import gui.admin.ActionProduct;
import gui.WelcomePage;
import gui.guest.DisplayPanel;
import gui.guest.ShowCase;

import utils.Validation;
import utils.DiscountProduct;
import utils.RegularProduct;
import utils.Product;
import utils.ProductManager;



public class WelcomeGUI {
    // ProductView productView = new ProductView();
    public static void main(String[] args) {
        
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.setSize(1280, 720);
        welcomePage.setLocationRelativeTo(null);
        welcomePage.setResizable(false);
        welcomePage.setVisible(true);

        // TEMPSTART
        // welcomePage.dispose();
        // DisplayPanel displayPanel = new DisplayPanel();
        // displayPanel.repaint();
        // TEMPEND
    }
}