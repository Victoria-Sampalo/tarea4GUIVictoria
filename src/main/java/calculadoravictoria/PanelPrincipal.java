/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoravictoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Victoria
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    //añadimos de los apuntes de GUI las anotaciones del profesor****
    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;

    //string para detectar si es numero u operando
    private String operando1 = "";
    private String operando2 = "";
    private String simboloOperacion = "";
    private String igualSimbolo = "";

    //constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

    }

    //método que verifica si es un número 
    private boolean comprobarNum(JTextArea areaTexto, String botonSiguiente) {

        try {
            Integer.parseInt(areaTexto.getText());
            Integer.parseInt(botonSiguiente);

        } catch (NumberFormatException nfe) {
            return false;

        }
        return true;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        //objeto que ejecuta el evento
        Object o = ae.getSource();

        //if para ver si es un botón y en ese caso desarrollo el codigo
        if (o instanceof JButton) {

            JButton aux = (JButton) o;

            System.out.println(aux.getText());

            //con el método de comprobar si es un numero comprobamos si es o no
            if (!comprobarNum(areaTexto, aux.getText())) {

                //añado operador 1 si esta vacio
                if (operando1.equals("")) {
                    operando1 = areaTexto.getText();
                    System.out.println("El operador 1 es: " + operando1);

                } else if (!operando1.equals("")) {

                    operando2 = areaTexto.getText().substring(operando1.length() + 1);
                    System.out.println("El operando 2 es: " + operando2);

                }
                //si tenemos una operación todavía por realizar
                if (aux.getText().equals("=") && operando2.equals("")) {

                    areaTexto.setText(operando1);

                } else if (aux.getText().equals("=") && !simboloOperacion.equals(null)) {
                    operando1 = "" + realizarOperacion(operando1 , operando2 , simboloOperacion);

                    areaTexto.setText(operando1);
                    operando2 = "";
                    simboloOperacion = "";
                    //control por scanner
                    System.out.println("Operando 1 es: " + operando1);
                    System.out.println("Operando 2 es: " + operando2);
                    System.out.println("El operando es: " + simboloOperacion);
                } //si no es igual, guado la operación
                else if (aux.getText().equals("+") || aux.getText().equals("-")
                        || aux.getText().equals("*") || aux.getText().equals("/")) {
                    simboloOperacion = aux.getText();
                    System.out.println("El operando es: " + simboloOperacion);

                }

            }
            //sino se limpia el scanner
            if (!aux.getText().equals("=")) {

                areaTexto.setText(areaTexto.getText() + aux.getText());

            }
            // control de C para que borre todo
            if (aux.getText().equalsIgnoreCase("C")) {
                //area de texto nulla al igual que el resto de los atributos
                areaTexto.setText("");
                operando1 = "";
                operando2 = "";
                simboloOperacion = "";

            }
            //damos a cada botón la función que debe de tener
            if (aux.getText().equals("+") || aux.getText().equals("-")
                    || aux.getText().equals("*") || aux.getText().equals("/")) {

                //for que me recorra la botonera 
                //hasta lenght -1 para evitar que se salga
                for (int i = 10; i < this.botonera.getgrupoBotones().length - 1; i++) {
                    this.botonera.getgrupoBotones()[i].setEnabled(false);

                }
            }

            //otro segundo for para borrar
            for (int i = 0; i < 10; i++) {
                if (aux.getText().equals(Integer.toString(i)) || aux.getText().equalsIgnoreCase("C")) {
                    for (int j = 10; j < this.botonera.getgrupoBotones().length - 1; j++) {
                        this.botonera.getgrupoBotones()[i].setEnabled(true);

                    }

                }

            }

        }
    
}
        //método para operar pasamos dos operandos y un signo operacion
    private float realizarOperacion(String num1, String num2, String operador) {
        float numero1, numero2;
        numero1 = Float.parseFloat(num1);
        numero2 = Float.parseFloat(num2);
        switch (operador) {
            case "+":
                return numero1 + numero2;
            case "/":
                return numero1 / numero2;
            case "*":
                return numero1 * numero2;
            case "-":
                return numero1 - numero2;
            default:
                return 0;
        }

    }
    
}
    
