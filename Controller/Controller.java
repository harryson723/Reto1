package Controller;

import Controller.Employed;
import Modelo.Model;
import Vistas.Index;
import Vistas.PanelChangePass;
import Vistas.PanelNavigation;
import Vistas.PersonTable;
import Vistas.ShowSucursalForm;
import Vistas.ShowUserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller implements ActionListener, MouseListener {

    // variables de vistas
    private Index index;
    private PanelChangePass panelChangePass;
    private PanelNavigation panelNavigation;
    private PersonTable personTable;
    private ShowUserForm showUserForm;
    private ShowSucursalForm showSucursalForm;
    private int indexWidth, indexHeight;
    private int idDireccion = 0;

    // variable de la parte logica
    private Model model;

    public Controller(Model model) {
        // instanciar modelo y vistas
        this.model = model;
        index = new Index();
        panelChangePass = new PanelChangePass();
        panelNavigation = new PanelNavigation();
        personTable = new PersonTable();
        showSucursalForm = new ShowSucursalForm(index, true);
        showUserForm = new ShowUserForm(index, true);
        initComponent();
    }

    private void initComponent() {
        // tamanos de las ventanas a mostrar
        indexWidth = 501;
        indexHeight = 419;
        int personTableWidth = 763;
        int personTableHeight = 385;
        int panelNavigationWidth = 753;
        int panelNavigationHeight = 414;
        int panelChangeWidth = 493;
        int panelChangenHeight = 345;
        index.setVisible(true);

        panelChangePass.setSize(panelChangeWidth, panelChangenHeight);
        panelNavigation.setSize(panelNavigationWidth, panelNavigationHeight);
        personTable.setSize(personTableWidth, personTableHeight);

        // anadir al jframe
        index.add(panelChangePass);
        index.add(panelNavigation);
        index.add(personTable);
        showWindow(index.panelPrincipal);
        // anadir escucha de eventos
        // eventos del inicion de sesion
        index.buttonLogin.addActionListener(this);
        index.buttonChangePass.addActionListener(this);
        // eventos del cambio de contrasena
        panelChangePass.buttonChangePass.addActionListener(this);
        // eventos del tabpane
        panelNavigation.buttonAddEmployed.addActionListener(this);
        panelNavigation.buttonAddAddress.addActionListener(this);
        panelNavigation.tableEmployed.addMouseListener(this);
        panelNavigation.buttonConsult.addActionListener(this);
        panelNavigation.buttonConsultDeparment.addActionListener(this);

        // eventos de persontable
        personTable.buttonCancel.addActionListener(this);
        personTable.buttonCreate.addActionListener(this);

        // eventos de showSucursalForm
        showSucursalForm.buttonNext.addActionListener(this);
        showSucursalForm.buttonBck.addActionListener(this);

        // eventos del showUserForm
        showUserForm.buttonBack.addActionListener(this);
        showUserForm.buttonDelete.addActionListener(this);
        showUserForm.buttonUpdate.addActionListener(this);

        // cargar las tablas
        panelNavigation.tableAddress.setModel(model.updateTableAddress(panelNavigation.tableAddress.getModel(), ""));
        panelNavigation.tableEmployed.setModel(model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), ""));
    }

    private void showWindow(JPanel panel) {
        index.panelPrincipal.setVisible(false);
        personTable.setVisible(false);
        panelNavigation.setVisible(false);
        panelChangePass.setVisible(false);
        panel.setVisible(true);
        index.setSize(panel.getWidth(), panel.getHeight() + 20);
        index.setLocationRelativeTo(null);
    }

    // ejecutar eventos de click en botones
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object e = ae.getSource();
        // verificar usuario y contraseña
        if (e == index.buttonLogin) {
            if (model.verifyUserPass(index.textUserName.getText(), index.textUserPass.getText())) {
                // mostrar ventana principal de la aplicacion
                showWindow(panelNavigation);
            }
        }
        // cambiar a formulario cambio de contraseña
        if (e == index.buttonChangePass) {
            showWindow(panelChangePass);
        }

        // cambiar contrase;a
        if (e == panelChangePass.buttonChangePass) {
            if (model.changePass(panelChangePass.textPass.getText(), panelChangePass.textPass1.getText())) {
                panelChangePass.textPass.setText("");
                panelChangePass.textPass1.setText("");
                index.panelPrincipal.setSize(indexWidth, indexHeight);
                showWindow(index.panelPrincipal);
            }
        }

        // consultar segun busqueda
        if (e == panelNavigation.buttonConsult) {
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), panelNavigation.textSearchEmp.getText());
        }

        if (e == panelNavigation.buttonAddEmployed) {
            // actualizar las sucursales disponibles
            panelNavigation.textSearchEmp.setText("");
            ArrayList<Object[]> arreglo = model.enumSucursal();
            Object[] sucursales = new Object[arreglo.size()];
            int n = sucursales.length;
            for (int i = 0; i < n; i++) {
                sucursales[i] = arreglo.get(i)[1];
            }
            ComboBoxModel enumSucursal = new DefaultComboBoxModel(sucursales);
            personTable.comboSucursal.setModel(enumSucursal);
            showWindow(personTable);
        }
        // añadir un nuevo empleado
        if (e == personTable.buttonCreate) {
            String firtsName = personTable.textFirtsName.getText();
            String lastName = personTable.textLastName.getText();
            String documentType = personTable.comboDocumentType.getSelectedItem().toString();
            String document = personTable.textDocument.getText();
            String email = personTable.textMail.getText();
            String sucursal = personTable.comboSucursal.getSelectedItem().toString();
            if (documentType.equals("SeleccionaUnaOpcion")) {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de documento");
            } else {
                Employed employed = new Employed(firtsName, lastName, email, document, documentType, 0);
                ArrayList<Object[]> arreglo = model.enumSucursal();
                model.createEmployed(employed, arreglo, sucursal);
                clearPersonTable();
            }

        }
        if (e == personTable.buttonCancel) {
            showWindow(panelNavigation);
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "");
            clearPersonTable();
        }

        // evento para anadir una direccion
        if (e == panelNavigation.buttonAddAddress) {
            // crear ventana para pedir nombre de la sucursal
            String departamento = panelNavigation.textDepartamento.getSelectedItem().toString();
            String zona = panelNavigation.textZona.getSelectedItem().toString();
            String tipoCalle = panelNavigation.textTipoCalle.getSelectedItem().toString();
            String numero1 = panelNavigation.textNumero1.getText();
            String numero2 = panelNavigation.textNumero2.getText();
            String numero3 = panelNavigation.textNumero3.getText();
            if (departamento.equals("SeleccionaUnaOpcion") || zona.equals("SeleccionaUnaOpcion") || tipoCalle.equals("SeleccionaUnaOpcion")) {
                JOptionPane.showMessageDialog(null, "Seleccione una Opcion");
            } else if (numero1.isEmpty() || numero2.isEmpty() || numero3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese los datos de la direccions");
            } else {
                showSucursalForm.setSize(320, 220);
                showSucursalForm.setVisible(true);
                index.getContentPane().add(showSucursalForm);
            }

        }

        // realizar el registro de la sucursal
        if (e == showSucursalForm.buttonNext) {
            if (showSucursalForm.textSucursalName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error, ingrese un nombre de sucursal");
            } else {
                String departamento = panelNavigation.textDepartamento.getSelectedItem().toString();
                String zona = panelNavigation.textZona.getSelectedItem().toString();
                String tipoCalle = panelNavigation.textTipoCalle.getSelectedItem().toString();
                String numero1 = panelNavigation.textNumero1.getText();
                String numero2 = panelNavigation.textNumero2.getText();
                String numero3 = panelNavigation.textNumero3.getText();
                if (model.createAddress(departamento, zona, tipoCalle, numero1, numero2, numero3)) {

                    idDireccion = model.getIdAddress(departamento, zona, tipoCalle, numero1, numero2, numero3);
                    model.createSucursal(idDireccion, showSucursalForm.textSucursalName.getText());
                    showSucursalForm.dispose();
                    showSucursalForm.textSucursalName.setText("");
                    clearAddress();
                    model.updateTableAddress(panelNavigation.tableAddress.getModel(), "");
                }

            }
        }
        
        // hacer consultas de departamento
        if(e == panelNavigation.buttonConsultDeparment) {
            if(panelNavigation.textConsultDeparment.getText().isEmpty()) {
                    model.updateTableAddress(panelNavigation.tableAddress.getModel(), "");
            } else {
                    model.updateTableAddress(panelNavigation.tableAddress.getModel(), panelNavigation.textConsultDeparment.getText());
            }
        
        }
        
        // cerrar jdialog de sucursal
        if (e == showSucursalForm.buttonBck) {
            try {
                showSucursalForm.dispose();
            } catch (Exception err) {
            }
            showSucursalForm.textSucursalName.setText("");
        }

        // eventos del show User FOrm
        if (e == showUserForm.buttonBack) {

            try {
                showUserForm.dispose();
            } catch (Exception errr) {
            }
        }

        if (e == showUserForm.buttonDelete) {
            model.deleteEmployed(Integer.parseInt(showUserForm.textId.getText()));
            try {
                showUserForm.dispose();
            } catch (Exception errr) {
            }
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "");
        }
        if (e == showUserForm.buttonUpdate) {
            int idEmp = Integer.parseInt(showUserForm.textId.getText());
            model.editEmployed(showUserForm.textFirtsName.getText(), showUserForm.textLastName.getText(), showUserForm.textMail.getText(), idEmp);
            showUserForm.dispose();
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "");
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object e = me.getSource();
        if (e == this.panelNavigation.tableEmployed) {
            int selectedRow = panelNavigation.tableEmployed.getSelectedRow();
            String idEmployed = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 0);
            String firtsNaame = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 1);
            String lastName = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 2);
            String document = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 4);
            String mail = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 5);
            showUserForm.textId.setText(idEmployed);
            showUserForm.textFirtsName.setText(firtsNaame);
            showUserForm.textLastName.setText(lastName);
            showUserForm.textDocument.setText(document);
            showUserForm.textMail.setText(mail);
            showUserForm.setVisible(true);
            index.getContentPane().add(showSucursalForm);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void clearPersonTable() {
        personTable.textDocument.setText("");
        personTable.textFirtsName.setText("");
        personTable.textLastName.setText("");
        personTable.textMail.setText("");
    }

    private void clearAddress() {
        panelNavigation.textNumero1.setText("");
        panelNavigation.textNumero2.setText("");
        panelNavigation.textNumero3.setText("");
    }

}
