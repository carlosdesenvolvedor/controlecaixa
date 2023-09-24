/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package contagem.caixa;

import br.com.centromusical.telas.TelaLogin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author usuario
 */
public class TelaCaixa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel figura;
    private ImageIcon imagem;

    public TelaCaixa() {
        initComponents();
    }

    public TelaCaixa(String nome, String adm) {
        initComponents();
        

        this.configurarTela();

        super.setTitle("Controle caixa");
        this.txtTotal.setText("0.0");

        this.lblHora.setText(obterDataHoraAtual());
        this.lblNomeUsuario.setText(nome);

        if (adm.equals("admim")) {
            this.preparandoMenu(true);
            lblNomeUsuario.setForeground(Color.red);
        } else {
            this.preparandoMenu(false);
            lblNomeUsuario.setForeground(Color.blue);
        }

    }

    public void calcular() {
        try {
            double c200 = 0.0;
            if (!n200.getText().isEmpty()) {
                c200 = Double.parseDouble(n200.getText());
                c200 *= 200;
            }

            double c100 = 0.0;
            if (!n100.getText().isEmpty()) {
                c100 = Double.parseDouble(n100.getText());
                c100 *= 100;
            }

            double c50 = 0.0;
            if (!n50.getText().isEmpty()) {
                c50 = Double.parseDouble(n50.getText());
                c50 *= 50;
            }

            double c20 = 0.0;
            if (!n20.getText().isEmpty()) {
                c20 = Double.parseDouble(n20.getText());
                c20 *= 20;
            }

            double c10 = 0.0;
            if (!n10.getText().isEmpty()) {
                c10 = Double.parseDouble(n10.getText());
                c10 *= 10;
            }

            double c5 = 0.0;
            if (!n5.getText().isEmpty()) {
                c5 = Double.parseDouble(n5.getText());
                c5 *= 5;
            }

            double c2 = 0.0;
            if (!n2.getText().isEmpty()) {
                c2 = Double.parseDouble(n2.getText());
                c2 *= 2;
            }

            double c1 = 0.0;
            if (!n1.getText().isEmpty()) {
                c1 = Double.parseDouble(n1.getText());
                c1 *= 1;
            }

            double c050 = 0.0;
            if (!n050.getText().isEmpty()) {
                c050 = Double.parseDouble(n050.getText());
                c050 *= 0.50;
            }

            double c025 = 0.0;
            if (!n025.getText().isEmpty()) {
                c025 = Double.parseDouble(n025.getText());
                c025 *= 0.25;
            }

            double c010 = 0.0;
            if (!n010.getText().isEmpty()) {
                c010 = Double.parseDouble(n010.getText());
                c010 *= 0.10;
            }

            double c005 = 0.0;
            if (!n005.getText().isEmpty()) {
                c005 = Double.parseDouble(n005.getText());
                c005 *= 0.05;
            }

            double meio = 0.0;
            if (!nMeio.getText().isEmpty()) {
                meio = Double.parseDouble(nMeio.getText());
            }

            DecimalFormat decimalFormat = new DecimalFormat("R$ 0.00");

            // Seu código para calcular os valores...
            double total = c200 + c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }

    }

    public String obterDataHoraAtual() {
        // Obtém a data e a hora atual
        Date now = new Date();

        // Formata a data e a hora em uma string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(now);

        // Retorna a string formatada
        return formattedDate;
    }

    public void componentResized(ComponentEvent e) {
        figura.setBounds(0, 0, getWidth(), getHeight());
    }

    //construtor
    private void configurarTela() {

        this.setSize(330, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        txtTotal.setToolTipText("Oi Lucas, gostou de passar o mouse aqui? kkk");
        figura = new JLabel();
        figura.setBounds(0, 0, getWidth(), getHeight());

        // Carregue uma imagem padrão ou forneça um caminho de imagem válido
        carregarImagem("src/img/fundoBranco.jpg");

        // Adicione o JLabel ao JFrame
        this.add(figura);

    }

    private void limpar() {
        n100.setText("");
        n50.setText("");
        n20.setText("");
        n10.setText("");
        n5.setText("");
        n2.setText("");
        n1.setText("");
        n050.setText("");
        n025.setText("");
        n010.setText("");
        n005.setText("");
        nMeio.setText("");
        txtTotal.setText("0,0");
        this.lblHora.setText(obterDataHoraAtual());

    }

    public void preparandoMenu(boolean perfil) {
        JMenuBar barraMenu = new JMenuBar();
        this.setJMenuBar(barraMenu);

        JMenu menuArquivo = new JMenu("Arquivo");
        barraMenu.add(menuArquivo);

        JMenuItem novoProjetoItem = new JMenuItem("Novo Projeto");
        novoProjetoItem.addActionListener((ActionEvent evt) -> {
            limpar();
        });
        menuArquivo.add(novoProjetoItem);

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar o programa?", "Sair do sistema", JOptionPane.YES_NO_OPTION);
                if (sair == 0) {
                    System.out.println("Programa finalizado com sucesso");
                    sairActionPerformed(evt);

                } else {
                    System.out.println("Continuando no sistema");
                }

            }
        });
        menuArquivo.add(sairItem);

        JMenuItem cadastrar = new JMenuItem("Cadastrar");
        cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TelaUsuario telaUsurio = new TelaUsuario();
                telaUsurio.setVisible(true);
            }
        });
        menuArquivo.add(cadastrar);
        cadastrar.setEnabled(perfil);

        JMenu menuFuncionalidades = new JMenu("Funcionalidades");
        barraMenu.add(menuFuncionalidades);

        JMenuItem calculadoraTrocoItem = new JMenuItem("Calculadora de Troco");
        calculadoraTrocoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                calculadoraTrocoActionPerformed(evt);
            }
        });
        menuFuncionalidades.add(calculadoraTrocoItem);
        calculadoraTrocoItem.setEnabled(false);

        JMenu menuAjuda = new JMenu("Ajuda");
        barraMenu.add(menuAjuda);

        JMenuItem sobreItem = new JMenuItem("Sobre");
        sobreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sobreActionPerformed(evt);
            }
        });
        menuAjuda.add(sobreItem);

    }

    private void sairActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void calculadoraTrocoActionPerformed(ActionEvent evt) {
        // Implemente a lógica para a calculadora de troco
        double totalCompra = 0.0;

        // Obtém o total da compra (sua lógica para obter esse valor)
        double valorPago = 0.0;

        // Obtém o valor pago pelo cliente (sua lógica para obter esse valor)
        double troco = valorPago - totalCompra;

        if (troco >= 0) {
            DecimalFormat decimalFormat = new DecimalFormat("R$ 0.00");
            javax.swing.JOptionPane.showMessageDialog(this, "Troco: " + decimalFormat.format(troco), "Troco", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Valor insuficiente para pagar a compra.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sobreActionPerformed(ActionEvent evt) {
        javax.swing.JOptionPane.showMessageDialog(this, "Controle de Caixa\nVersão 1.0\n© Seu Nome", "Sobre", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarSaldo(double novoSaldo) {
        // Atualize os campos de texto correspondentes com as novas quantidades de notas e moedas
        n100.setText(String.valueOf((int) (novoSaldo / 100)));
        novoSaldo %= 100;

        n50.setText(String.valueOf((int) (novoSaldo / 50)));
        novoSaldo %= 50;

        n20.setText(String.valueOf((int) (novoSaldo / 20)));
        novoSaldo %= 20;

        n10.setText(String.valueOf((int) (novoSaldo / 10)));
        novoSaldo %= 10;

        n5.setText(String.valueOf((int) (novoSaldo / 5)));
        novoSaldo %= 5;

        n2.setText(String.valueOf((int) (novoSaldo / 2)));
        novoSaldo %= 2;

        n1.setText(String.valueOf((int) novoSaldo));
        novoSaldo -= (int) novoSaldo;

        n050.setText(String.valueOf((int) (novoSaldo / 0.50)));
        novoSaldo %= 0.50;

        n025.setText(String.valueOf((int) (novoSaldo / 0.25)));
        novoSaldo %= 0.25;

        n010.setText(String.valueOf((int) (novoSaldo / 0.10)));
        novoSaldo %= 0.10;

        n005.setText(String.valueOf((int) (novoSaldo / 0.05)));
        novoSaldo -= (int) (novoSaldo / 0.05);

        nMeio.setText(String.valueOf(novoSaldo));
    }

    public void carregarImagem(String caminho) {
        if (caminho == null || caminho.isEmpty()) {
            caminho = "src/img/fundoBranco.jpg";
            imagem = new ImageIcon(caminho);
            figura = new JLabel(imagem);
            this.add(figura);

        }
        File arquivo = new File(caminho);
        if (arquivo.exists()) {
            imagem = new ImageIcon(caminho);
            figura.setIcon(imagem);
            figura.setBounds(0, 0, this.getWidth(), this.getHeight()); // Defina os limites usando as dimensões do JFrame
            figura.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "O caminho é invalido");

        }

    }

    public void atualizarTxtTotal(double novoTotal) {
        DecimalFormat decimalFormat = new DecimalFormat("R$ 0.00");
        txtTotal.setText(decimalFormat.format(novoTotal));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        n100 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        n50 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        n20 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        n10 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        n5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        n2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nMeio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        n1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        n050 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        n025 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        n010 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        n005 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        n200 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(420, 590));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setText("Usuário: ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 72, -1, -1));

        lblNomeUsuario.setText("##");
        jPanel1.add(lblNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 72, 178, -1));

        lblHora.setText("/ /");
        jPanel1.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 42, -1, -1));

        jLabel14.setText("Data:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 42, -1, -1));

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel15.setText("Coloque a qtd de celula ou moeda");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        jLabel1.setText("Nota de R$ 100,00");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        n100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n100ActionPerformed(evt);
            }
        });
        jPanel1.add(n100, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 45, -1));

        jLabel2.setText("Nota de R$ 50,00");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

        n50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n50ActionPerformed(evt);
            }
        });
        jPanel1.add(n50, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 47, -1));

        jLabel3.setText("Nota de R$ 20,00");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 187, -1, -1));

        n20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n20ActionPerformed(evt);
            }
        });
        jPanel1.add(n20, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 184, 47, -1));

        jLabel4.setText("Nota de R$ 10,00");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 217, -1, -1));

        n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n10ActionPerformed(evt);
            }
        });
        jPanel1.add(n10, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 214, 47, -1));

        jLabel5.setText("Nota de R$ 5,00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 247, -1, -1));

        n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n5ActionPerformed(evt);
            }
        });
        jPanel1.add(n5, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 244, 47, -1));

        jLabel6.setText("Nota de R$ 2,00");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 277, -1, -1));

        n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n2ActionPerformed(evt);
            }
        });
        jPanel1.add(n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 274, 47, -1));

        jLabel7.setText("Total meio cx. ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 307, -1, -1));

        nMeio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nMeioActionPerformed(evt);
            }
        });
        jPanel1.add(nMeio, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 304, 47, -1));

        jLabel8.setText("Moeda R$ 1,00");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 337, -1, -1));

        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });
        jPanel1.add(n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 334, 47, -1));

        jLabel9.setText("Moeda R$ 0,50");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 367, -1, -1));

        n050.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n050ActionPerformed(evt);
            }
        });
        jPanel1.add(n050, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 364, 47, -1));

        jLabel10.setText("Moeda R$ 0,25");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 394, -1, -1));

        n025.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n025ActionPerformed(evt);
            }
        });
        jPanel1.add(n025, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 394, 46, -1));

        jLabel11.setText("Moeda R$ 0,10");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 427, -1, -1));

        n010.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n010ActionPerformed(evt);
            }
        });
        jPanel1.add(n010, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 424, 46, -1));

        jLabel12.setText("Moeda R$ 0,05");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 457, -1, -1));

        n005.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n005ActionPerformed(evt);
            }
        });
        jPanel1.add(n005, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 454, 46, -1));

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel13.setText("Total Caixa:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 484, -1, -1));

        txtTotal.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        txtTotal.setText("R$");
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 484, 132, -1));

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 541, 84, -1));

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 512, -1, -1));

        jButton4.setText("Finalizar");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));

        jButton1.setText("Retirada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 541, -1, -1));

        jButton3.setText("Entrada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, 90, -1));

        jLabel17.setText("Nota de R$ 200,00");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        n200.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n200ActionPerformed(evt);
            }
        });
        jPanel1.add(n200, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 45, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(472, 635));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void n50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n50ActionPerformed
        // TODO add your handling code here:
        calcular();
        n20.requestFocus();
    }//GEN-LAST:event_n50ActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:

        calcular();

    }//GEN-LAST:event_btnCalcularActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        n100.setText("");
        n50.setText("");
        n20.setText("");
        n10.setText("");
        n5.setText("");
        n2.setText("");
        n1.setText("");
        n050.setText("");
        n025.setText("");
        n010.setText("");
        n005.setText("");
        nMeio.setText("");
        txtTotal.setText("0,0");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double valorRetirada = 0.0;

        // Obtém o valor da retirada (sua lógica para obter esse valor)
        valorRetirada = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a retirada"));

        try {
            double saldoAtual = Double.parseDouble(txtTotal.getText().replace("R$ ", "").replace(",", "."));

            if (valorRetirada > 0 && valorRetirada <= saldoAtual) {
                double novoSaldo = saldoAtual - valorRetirada;

                DecimalFormat decimalFormat = new DecimalFormat("0.00"); // Formato sem o "R$"
                String nSaldo = decimalFormat.format(novoSaldo);

                txtTotal.setText("R$ " + nSaldo); // Adicione o "R$" na exibição final
                javax.swing.JOptionPane.showMessageDialog(this, "Retirada realizada com sucesso.\nNovo saldo: R$ " + nSaldo, "Retirada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Valor de retirada inválido ou saldo insuficiente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular: " + e);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nome = JOptionPane.showInputDialog(null, "Digite o nome de quem está fazendo a entrada: ");
        double valorEntrada = 0.0;

        // Obtém o valor da retirada (sua lógica para obter esse valor)
        valorEntrada = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a retirada"));

        try {
            double saldoAtual = Double.parseDouble(txtTotal.getText().replace("R$ ", "").replace(",", "."));

            if (valorEntrada > 0 && txtTotal.getText() != null) {
                double novoSaldo = saldoAtual + valorEntrada;

                DecimalFormat decimalFormat = new DecimalFormat("0.00"); // Formato sem o "R$"
                String nSaldo = decimalFormat.format(novoSaldo);

                txtTotal.setText("R$ " + nSaldo); // Adicione o "R$" na exibição final
                javax.swing.JOptionPane.showMessageDialog(this, "Entrada realizada com sucesso. por: " + nome + "\nNovo saldo: R$ " + nSaldo, "Retirada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Valor de entrada inválido ", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular: " + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void n100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n100ActionPerformed
        // TODO add your handling code here:
        btnCalcularActionPerformed(evt);
        n50.requestFocus();

    }//GEN-LAST:event_n100ActionPerformed

    private void n20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n20ActionPerformed
        // TODO add your handling code here:
        calcular();
        n10.requestFocus();
    }//GEN-LAST:event_n20ActionPerformed

    private void n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n10ActionPerformed
        // TODO add your handling code here:
        calcular();
        n5.requestFocus();
    }//GEN-LAST:event_n10ActionPerformed

    private void n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n5ActionPerformed
        // TODO add your handling code here:
        calcular();
        n2.requestFocus();
    }//GEN-LAST:event_n5ActionPerformed

    private void n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n2ActionPerformed
        // TODO add your handling code here:
        calcular();
        nMeio.requestFocus();
    }//GEN-LAST:event_n2ActionPerformed

    private void nMeioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nMeioActionPerformed
        // TODO add your handling code here:
        calcular();
        n1.requestFocus();

    }//GEN-LAST:event_nMeioActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
        // TODO add your handling code here:
        calcular();
        n050.requestFocus();

    }//GEN-LAST:event_n1ActionPerformed

    private void n050ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n050ActionPerformed
        calcular();
        n025.requestFocus();
    }//GEN-LAST:event_n050ActionPerformed

    private void n025ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n025ActionPerformed
        calcular();
        n010.requestFocus();
    }//GEN-LAST:event_n025ActionPerformed

    private void n010ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n010ActionPerformed
        calcular();
        n005.requestFocus();
    }//GEN-LAST:event_n010ActionPerformed

    private void n005ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n005ActionPerformed
        // TODO add your handling code here:
        calcular();
        btnCalcular.requestFocus();
    }//GEN-LAST:event_n005ActionPerformed

    private void n200ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n200ActionPerformed
        // TODO add your handling code here:
        calcular();
        n100.requestFocus();
    }//GEN-LAST:event_n200ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JTextField n005;
    public javax.swing.JTextField n010;
    public javax.swing.JTextField n025;
    public javax.swing.JTextField n050;
    public javax.swing.JTextField n1;
    public javax.swing.JTextField n10;
    public javax.swing.JTextField n100;
    public javax.swing.JTextField n2;
    public javax.swing.JTextField n20;
    public javax.swing.JTextField n200;
    public javax.swing.JTextField n5;
    public javax.swing.JTextField n50;
    public javax.swing.JTextField nMeio;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
