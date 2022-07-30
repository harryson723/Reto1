package Controller;

import Controller.Employed;
import Modelo.EnumDepartamento;
import Modelo.EnumTipoCalle;
import Modelo.EnumZona;
import Modelo.Model;
import Vistas.Index;
import Vistas.PanelChangePass;
import Vistas.PanelNavigation;
import Vistas.PersonTable;
import Vistas.PuestoTrabajo;
import Vistas.ShowSucursalForm;
import Vistas.ShowUserForm;
import Vistas.SucursalManage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller implements ActionListener, MouseListener, KeyListener {

    // variables de vistas
    private Index index;
    private PanelChangePass panelChangePass;
    private PanelNavigation panelNavigation;
    private PersonTable personTable;
    private ShowUserForm showUserForm;
    private ShowSucursalForm showSucursalForm;
    private SucursalManage sucursalManage;
    private PuestoTrabajo puestoTrabajo;
    private int indexWidth, indexHeight;
    private int idDireccion = 0, idEmployed = 0;

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
        sucursalManage = new SucursalManage(index, true);
        puestoTrabajo = new PuestoTrabajo(index, true);
        initComponent();
    }

    private void initComponent() {
        // tamanos de las ventanas a mostrar
        indexWidth = 501;
        indexHeight = 419;
        int personTableWidth = 763;
        int personTableHeight = 485;
        int panelNavigationWidth = 813;
        int panelNavigationHeight = 504;
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
        index.textUserName.addKeyListener(this);
        index.textUserPass.addKeyListener(this);
        initComponentWork();
    }

    public void initComponentWork() {
        // eventos del cambio de contrasena
        panelChangePass.buttonChangePass.addActionListener(this);
        // eventos del tabpane
        panelNavigation.buttonAddEmployed.addActionListener(this);
        panelNavigation.buttonAddAddress.addActionListener(this);
        panelNavigation.tableEmployed.addMouseListener(this);
        panelNavigation.buttonConsult.addActionListener(this);
        panelNavigation.buttonConsultDeparment.addActionListener(this);
        panelNavigation.tableAddress.addMouseListener(this);
        panelNavigation.comboSucursal.addActionListener(this);
        panelNavigation.buttonChangeToEmployed.addActionListener(this);

        // eventos de persontable
        personTable.buttonCancel.addActionListener(this);
        personTable.buttonCreate.addActionListener(this);
        personTable.buttonUploadImg.addActionListener(this);
        personTable.comboSucursal.addActionListener(this);

        // eventos de showSucursalForm
        showSucursalForm.buttonNext.addActionListener(this);
        showSucursalForm.buttonBck.addActionListener(this);

        // eventos del showUserForm
        showUserForm.buttonBack.addActionListener(this);
        showUserForm.buttonDelete.addActionListener(this);
        showUserForm.buttonUpdate.addActionListener(this);

        // eventos del sucursal manage
        sucursalManage.buttonActualizar.addActionListener(this);
        sucursalManage.buttonCancelar.addActionListener(this);
        sucursalManage.buttonEliminar.addActionListener(this);

        // eventos del tipo puesto de trabajo
        puestoTrabajo.buttonCancel.addActionListener(this);
        puestoTrabajo.buttonSave.addActionListener(this);
        // cargar las tablas
        panelNavigation.tableEmployed.setModel(model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "", false));
        panelNavigation.tableAddress.setModel(model.updateTableAddress(panelNavigation.tableAddress.getModel(), ""));

        updateComboSucursal(panelNavigation.comboSucursal);
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
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), panelNavigation.textSearchEmp.getText(), false);
        }

        if (e == panelNavigation.buttonAddEmployed) {
            // actualizar las sucursales disponibles
            panelNavigation.textSearchEmp.setText("");
            updateComboSucursal(personTable.comboSucursal);
            showWindow(personTable);
        }
        // añadir un nuevo empleado
        if (e == personTable.buttonCreate) {
            String firtsName = personTable.textFirtsName.getText();
            String lastName = personTable.textLastName.getText();
            String documentType = personTable.comboDocumentType.getSelectedItem().toString();
            String document = personTable.textDocument.getText();
            String email = personTable.textMail.getText();
            Sucursal sucursal = (Sucursal) personTable.comboSucursal.getSelectedItem();
            JobType employedType = (JobType) personTable.comboTipoEmpleado.getSelectedItem();
            
            if (documentType.equals("SeleccionaUnaOpcion")) {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de documento");
            } else {
                Employed employed = new Employed(firtsName, lastName, email, document, documentType, employedType, 0);

                model.createEmployed(employed, sucursal);
                clearPersonTable();
            }

        }
        if (e == personTable.buttonCancel) {
            showWindow(panelNavigation);
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "", false);
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
                    updateComboSucursal(panelNavigation.comboSucursal);
                }

            }
        }

        // hacer consultas de departamento
        if (e == panelNavigation.buttonConsultDeparment) {
            if (panelNavigation.textConsultDeparment.getText().isEmpty()) {
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

        // eliminar un empleado
        if (e == showUserForm.buttonDelete) {
            model.deleteEmployed(showUserForm.idEmployed);
            try {
                showUserForm.dispose();
            } catch (Exception errr) {
            }
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "", false);
        }
        if (e == showUserForm.buttonUpdate) {
         
            model.editEmployed(showUserForm.textFirtsName.getText(), showUserForm.textLastName.getText(), showUserForm.textMail.getText(), showUserForm.idEmployed);
            showUserForm.dispose();
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "", false);
        }
        // actualizar la sucursal seleccionada
        if (e == sucursalManage.buttonActualizar) {
            String sucursal = sucursalManage.textSucursal.getText();
            String numero1 = sucursalManage.textNumero1.getText();
            String numero2 = sucursalManage.textNumero2.getText();
            String numero3 = sucursalManage.textNumero3.getText();
            String departamento = sucursalManage.comboDepartamento.getSelectedItem().toString();
            String calle = sucursalManage.comboCalle.getSelectedItem().toString();
            String zona = sucursalManage.comboZona.getSelectedItem().toString();
            if (sucursal.isEmpty() || numero1.isEmpty() || numero2.isEmpty() || numero3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error, ningun campo puede estar vacio ", "Error Campos vacios", 0);
            } else if (departamento.equals("SeleccionaUnaOpcion") || calle.equals("SeleccionaUnaOpcion") || zona.equals("SeleccionaUnaOpcion")) {
                JOptionPane.showMessageDialog(null, "Error, Seleccione debe seleccionar (Departamento, Calle, Z0na)", "Error ComboBox sin seleccionar", 0);
            } else {
                model.updateSucursalInfo(sucursal, numero1, numero2, numero3, calle, zona, departamento, sucursalManage.idSucursal);
                model.updateTableAddress(panelNavigation.tableAddress.getModel(), "");
                sucursalManage.dispose();
            }
        }

        if (e == sucursalManage.buttonCancelar) {
            sucursalManage.dispose();
        }

        // eliminar una sucursal 
        if (e == sucursalManage.buttonEliminar) {
            model.deleteSucursal(sucursalManage.idSucursal);
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), "", false);
            model.updateTableAddress(panelNavigation.tableAddress.getModel(), "");
            sucursalManage.dispose();
        }

        // manejar eventos del combosucursal para busqueda
        if (e == panelNavigation.comboSucursal) {
            Sucursal selectedItem = (Sucursal) panelNavigation.comboSucursal.getSelectedItem();
            model.updateTableEmployed(panelNavigation.tableEmployed.getModel(), selectedItem.getNombreSucursal(), true);
        }

        // mostrar panel de puestos de trabajo
        if (e == panelNavigation.buttonChangeToEmployed) {
            updateComboSucursal(puestoTrabajo.comboSucursal);
            puestoTrabajo.setVisible(true);

        }

        // eventos de creacion y cerrado de cracion de puestos en la base de datos
        if (e == puestoTrabajo.buttonCancel) {
            puestoTrabajo.dispose();
        }

        if (e == puestoTrabajo.buttonSave) {
            Sucursal sucursal = (Sucursal) puestoTrabajo.comboSucursal.getSelectedItem();
            String puesto = puestoTrabajo.comboPuesto.getSelectedItem().toString();
            String salario = puestoTrabajo.textSalario.getText();

            model.insertJob(sucursal.getIdSucursal(), sucursal.getNombreSucursal(), puesto, salario);
        }

        if (e == personTable.comboSucursal) {
            Sucursal sucursal = (Sucursal) personTable.comboSucursal.getSelectedItem();
            ArrayList<JobType> arreglo = model.getComboJobType(sucursal);
            if (arreglo != null) {
                
                ComboBoxModel enumJobType = new DefaultComboBoxModel(arreglo.toArray());
                personTable.comboTipoEmpleado.setModel(enumJobType);
            } else {
                ComboBoxModel enumJobType = new DefaultComboBoxModel(new Object[]{"Seleccione una opcion"});
                personTable.comboTipoEmpleado.setModel(enumJobType);
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object e = me.getSource();
        if (e == this.panelNavigation.tableEmployed) {
            int selectedRow = panelNavigation.tableEmployed.getSelectedRow();

            String firtsNaame = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 0);
            String lastName = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 1);
            String document = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 3);
            String mail = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 4);
            String sucursal = (String) panelNavigation.tableEmployed.getValueAt(selectedRow, 5);
            int idEmployed = model.getIdEmployed(firtsNaame, lastName, document, mail, sucursal);

            // cargar las sucursales disponnibles
            updateComboSucursal(showUserForm.comboSucursal);
            // colocar informacion del empleado seleccionado 
            showUserForm.idEmployed = idEmployed;
            showUserForm.textFirtsName.setText(firtsNaame);
            showUserForm.textLastName.setText(lastName);
            showUserForm.textDocument.setText(document);
            showUserForm.textMail.setText(mail);
            showUserForm.setVisible(true);

        }

        if (e == panelNavigation.tableAddress) {

            int selectedRow = panelNavigation.tableAddress.getSelectedRow();
            String sucursalName = (String) panelNavigation.tableAddress.getValueAt(selectedRow, 0);
            String sucursalDepart = (String) panelNavigation.tableAddress.getValueAt(selectedRow, 1);
            int idSucursal = model.getIdSucursal(sucursalName, sucursalDepart);
            //  crear el combobbox mode
            ComboBoxModel enumZona, enumTipoCalle, enumDepartamento;
            enumZona = new DefaultComboBoxModel(EnumZona.values());
            enumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
            enumDepartamento = new DefaultComboBoxModel(EnumDepartamento.values());

            // informacion de la sucursal
            sucursalManage.idSucursal = idSucursal;
            String[] data = model.getSucursalInfo(idSucursal);
            sucursalManage.textSucursal.setText(data[0]);
            sucursalManage.textNumero1.setText(data[4]);
            sucursalManage.textNumero2.setText(data[5]);
            sucursalManage.textNumero3.setText(data[6]);
            // informacion en los combobox
            enumDepartamento.setSelectedItem(data[1]);
            sucursalManage.comboDepartamento.setModel(enumDepartamento);
            enumZona.setSelectedItem(data[2]);
            sucursalManage.comboZona.setModel(enumZona);
            enumTipoCalle.setSelectedItem(data[3]);
            sucursalManage.comboCalle.setModel(enumTipoCalle);

            sucursalManage.setVisible(true);
        }

        // subir imagenes
        if (e == personTable.buttonUploadImg) {
            System.out.println("Boton imagen");
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

    @Override
    public void keyTyped(KeyEvent ae) {

    }

    @Override
    public void keyPressed(KeyEvent ae) {
        Object e = ae.getSource();

        if (KeyEvent.getKeyText(ae.getKeyCode()).equals("Enter")) {
            // verificar usuario y contraseña
            if (e == index.textUserName || e == index.textUserPass) {
                if (model.verifyUserPass(index.textUserName.getText(), index.textUserPass.getText())) {
                    // mostrar ventana principal de la aplicacion
                    showWindow(panelNavigation);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void updateComboSucursal(JComboBox combo) {
        // actualizar las sucursales disponibles en el panel de navegacion para la busqueda
        ArrayList<Object[]> arreglo = model.enumSucursal();
        Object[] sucursales = new Object[arreglo.size()];
        int n = sucursales.length;
        for (int i = 0; i < n; i++) {
            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal((int) arreglo.get(i)[0]);
            sucursal.setNombreSucursal((String) arreglo.get(i)[1]);
            sucursales[i] = sucursal;
        }
        ComboBoxModel enumSucursal = new DefaultComboBoxModel(sucursales);
        combo.setModel(enumSucursal);
    }

}
