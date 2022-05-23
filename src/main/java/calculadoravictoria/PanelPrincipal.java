/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoravictoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Victoria
 */
public class PanelPrincipal extends JPanel implements ActionListener{
    //añadimos de los apuntes de GUI las anotaciones del profesor****
    // Atributos de la clase (privados)
	private PanelBotones botonera;
	private JTextArea areaTexto;
	private int tipoOperacion;
        
        
        //string para detectar si es numero u operando
        
       private String operando1 ="";
       private String operando2 ="";
       private String simboloOperacion= "";
       private String igualSimbolo="";
        
    //constructor
        public PanelPrincipal(){
    		initComponents();
    		tipoOperacion = -1; // No hay operaciones en la calculadora
	}
    

        
        // Se inicializan los componentes gráficos y se colocan en el panel
	private void initComponents(){
    		// Creamos el panel de botones
    		botonera = new PanelBotones();
    		// Creamos el área de texto
    		areaTexto = new JTextArea(10,50);
    		areaTexto.setEditable(false);
    		areaTexto.setBackground(Color.white);
   
    		//Establecemos layout del panel principal
    		this.setLayout(new BorderLayout());
    		// Colocamos la botonera y el área texto
                this.add(areaTexto, BorderLayout.NORTH);
                this.add(botonera, BorderLayout.SOUTH);
    		
    		
	 
	}
        
        
        //método que verifica si es un número 
        private boolean comprobarNum (JTextArea areaTexto, String botonSiguiente){
        
            try {
            Integer.parseInt(areaTexto.getText());
            Integer.parseInt(botonSiguiente);
            
            
            }catch(NumberFormatException nfe){
             return false;
            
            }
            return true;
        
        }

@Override
	public void actionPerformed(ActionEvent ae) {
        
        //objeto que ejecuta el evento
        Object o = ae.getSource();
        
        //if para ver si es un botón y en ese caso desarrollo el codigo
        if(o instanceof Jbutton){
        
        Jbutton aux = (JButton) o;
        
        System.out.println(aux.getText());
        
        //con el método de comprobar si es un numero comprobamos si es o no
        if (!comprobarNum(areaTexto, aux.getTexto())) {
        
        
        
        
        }
        //sino se limpia el scanner
        if(!aux.getText().equals("=")){
        
            areaTexto.setText(areaTexto.getText() + aux.getText());
            
        
        }
        
        
        
        
        }
        
        }
}
