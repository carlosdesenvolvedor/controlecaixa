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

    }

    public TelaCaixa(String nome, String adm) {
        initComponents();
        setLocationRelativeTo(null);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 900));

        jLabel16.setText("Usuário: ");

        lblNomeUsuario.setText("##");

        lblHora.setText("/ /");

        jLabel14.setText("Data:");

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel15.setText("Coloque a qtd de celula ou moeda");

        jLabel1.setText("Nota de R$ 100,00");

        n100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n100ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nota de R$ 50,00");

        n50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n50ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nota de R$ 20,00");

        n20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n20ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nota de R$ 10,00");

        n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n10ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nota de R$ 5,00");

        n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Nota de R$ 2,00");

        n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Total meio cx. ");

        nMeio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nMeioActionPerformed(evt);
            }
        });

        jLabel8.setText("Moeda R$ 1,00");

        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Moeda R$ 0,50");

        n050.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n050ActionPerformed(evt);
            }
        });

        jLabel10.setText("Moeda R$ 0,25");

        n025.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n025ActionPerformed(evt);
            }
        });

        jLabel11.setText("Moeda R$ 0,10");

        n010.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n010ActionPerformed(evt);
            }
        });

        jLabel12.setText("Moeda R$ 0,05");

        n005.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n005ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel13.setText("Total Caixa:");

        txtTotal.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        txtTotal.setText("R$");

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jButton4.setText("Finalizar");

        jButton1.setText("Retirada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Entrada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(n20, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(n50, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nMeio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(n10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n100, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHora)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnCalcular)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(n050, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                    .addGap(28, 28, 28)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(n010, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(n025, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(n005, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel15)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblNomeUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(n100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(n50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(n20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(n10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nMeio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(n050, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(n025, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(n010, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(n005, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(1, 1, 1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTotal))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCalcular))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(398, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void n50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n50ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n20.requestFocus();
    }//GEN-LAST:event_n50ActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }


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
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n10.requestFocus();
    }//GEN-LAST:event_n20ActionPerformed

    private void n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n10ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n5.requestFocus();
    }//GEN-LAST:event_n10ActionPerformed

    private void n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n5ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n2.requestFocus();
    }//GEN-LAST:event_n5ActionPerformed

    private void n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n2ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        nMeio.requestFocus();
    }//GEN-LAST:event_n2ActionPerformed

    private void nMeioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nMeioActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n1.requestFocus();

    }//GEN-LAST:event_nMeioActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n050.requestFocus();

    }//GEN-LAST:event_n1ActionPerformed

    private void n050ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n050ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n025.requestFocus();
    }//GEN-LAST:event_n050ActionPerformed

    private void n025ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n025ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n010.requestFocus();
    }//GEN-LAST:event_n025ActionPerformed

    private void n010ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n010ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        n005.requestFocus();
    }//GEN-LAST:event_n010ActionPerformed

    private void n005ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n005ActionPerformed
        // TODO add your handling code here:
        try {

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
            double total = c100 + c50 + c20 + c10 + c5 + c2 + c1 + c050 + c025 + c010 + c005 + meio;
            txtTotal.setText(decimalFormat.format(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "não é permitido simbolos, numeros com virgula nem letras, somente números inteiro");

        }
        btnCalcular.requestFocus();
    }//GEN-LAST:event_n005ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);

            }
        });
    }

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
    public javax.swing.JTextField n5;
    public javax.swing.JTextField n50;
    public javax.swing.JTextField nMeio;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
