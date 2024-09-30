/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.projeto.cadastro;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Job
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    
        private void carregaTabelaUsuarios(){
        CadastroDAO list = new CadastroDAO();
        try {
            List<Cadastro> usuarios = list.listarUsuarios();
            if(usuarios.isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado");
                return;
            }
            //Colunas da tabela 
             String[] colunas = {"ID","NOME","CPF","DATA NASCIMENTO","ALTURA"}; 
            Object[][] dados = new Object[usuarios.size()][5];
            
            int i = 0;
            for(Cadastro usuario:usuarios){
                dados[i][0]=usuario.getId();
                dados[i][1]=usuario.getNome();
                dados[i][2]=usuario.getCpf();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataNascimento = usuario.getData_nascimento();
                        
                dados[i][3]=dataNascimento != null ? sdf.format(dataNascimento):"";
                dados[i][4]=usuario.getAltura();
                i++;  
            }
            
            //Define o modelo da tabela
            
            DefaultTableModel model = new DefaultTableModel(dados,colunas){

                @Override
            public boolean isCellEditable(int row, int column) {
            //Permitir a edição de todas as colunas, exceto a coluna de ID
                return column != 0;
                }
                };
                tblUsuarios.setModel(model); 
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(this, "Erro ao listar"+erro.getMessage());
        }
    }
    public TelaPrincipal() {
        initComponents();
        
        //Aceitar apenas letras
        txtNome.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent evt){
                char c = evt.getKeyChar();
                //Permite apenas letras e espaços, impede numeros ou simbolos
                if(!Character.isLetter(c) && c !=' '){
                    evt.consume();//bloqueia o caracter invalido
                }
            }
        });
        
        //Aceitar só numeros
        txtCPF.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent evt){
                char c = evt.getKeyChar();
                //permite apenas numeros
                if(!Character.isDigit(c)){
                    evt.consume();//bloqueia o caracter invalido
                }
                // Limita a 11 caracteres
                if (txtCPF.getText().length() >= 11) {
                    evt.consume(); // Bloqueia a digitação se já tiver 11 caracteres
                }
            }
            

        });
        //
        txtDataNascimento.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent evt){
                char c = evt.getKeyChar();
                String text = txtDataNascimento.getText();
                //Permitir apenas numeros e "/"
                if(!Character.isDigit(c)&& c != '/'){
                    evt.consume();
                }
                //controle o formato da data
                if(text.length()==2 || text.length()==5){
                    txtDataNascimento.setText(text + "/");
                }
                //limite o numero de caracter
                if(text.length()>=10){
                    evt.consume();
                }
            }
        });
        //Campo de altura
        txtAltura.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent evt){
                char c = evt.getKeyChar();
                String text = txtAltura.getText();
                //permite apenas digitos, ponto ou virgula
                if(!Character.isDigit(c) && c != '.' && c != ','){
                    evt.consume();
                }
                //permite apenas um ponto ou virgula
                if((c == '.' || c == ',') && (text.contains(".")|| text.contains(",")))
                    evt.consume();
            }
        });
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnBuscaID = new javax.swing.JButton();
        btnBuscaUsuarios = new javax.swing.JButton();
        btnSalvarAlteracoes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtDataNascimento = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        lblResultado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtExcluirID = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("CADASTRO DE USUARIOS");

        jLabel2.setText("Nome: ");

        jLabel3.setText("CPF: ");

        jLabel4.setText("Data de Nascimento: ");

        jLabel5.setText("Altura: ");

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel6.setText("ID: ");

        btnBuscaID.setText("BUSCA ID");
        btnBuscaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaIDActionPerformed(evt);
            }
        });

        btnBuscaUsuarios.setText("BUSCA USUARIOS");
        btnBuscaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaUsuariosActionPerformed(evt);
            }
        });

        btnSalvarAlteracoes.setText("SALVAR ALTERAÇÔES");
        btnSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlteracoesActionPerformed(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "CPF", "DATA DE NASCIMENTO", "ALTURA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);
        if (tblUsuarios.getColumnModel().getColumnCount() > 0) {
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setText("by Fabio de Sousa Soares");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });

        txtAltura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlturaActionPerformed(evt);
            }
        });

        lblResultado.setBackground(new java.awt.Color(0, 204, 204));
        lblResultado.setText("            ");

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Excluir Cliente");

        txtExcluirID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExcluirIDActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir ID");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel8)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtExcluirID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(txtExcluirID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCPF)
                                    .addComponent(txtNome)
                                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAltura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnSalvar))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscaID))
                                .addComponent(btnBuscaUsuarios))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(btnSalvarAlteracoes))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaID)
                    .addComponent(lblResultado))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscaUsuarios)
                    .addComponent(btnSalvarAlteracoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtAlturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlturaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
                Cadastro cadastro = new Cadastro(); 
                cadastro.setNome(txtNome.getText());
                cadastro.setCpf(txtCPF.getText());
                
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataNascimento = formatoData.parse(txtDataNascimento.getText());
            cadastro.setData_nascimento(dataNascimento);
            
        } catch (ParseException erro) {
            JOptionPane.showMessageDialog(this, "formato invalido");
            return;
        }
        try {
            float altura = Float.parseFloat(txtAltura.getText());
            cadastro.setAltura(altura);
            
            
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Formato Inválido");
            return;
        }
        CadastroDAO cadastroDAO = new CadastroDAO();
        cadastroDAO.criarUsuario(cadastro);
        JOptionPane.showMessageDialog(this, "Cadastrado com sucesso");
              
        //limpa os campos
        
        txtNome.setText("");
        txtCPF.setText("");
        txtDataNascimento.setText("");
        txtAltura.setText("");
     
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnBuscaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaIDActionPerformed
        // TODO add your handling code here:
        
                CadastroDAO usua = new CadastroDAO();
        try {
            //Obtém o ID digitado no txtID
            int id = Integer.parseInt(txtID.getText());
            //Busca o usuario pelo ID através do objeto UsuarioDAO
            Cadastro usuario = usua.buscarUsuarioPorId(id);
            //Verifica se o usuario foi encontrado
            if (usuario != null) {
                //Exibe os dados no lblResultado
                lblResultado.setText(
                        "ID: " + usuario.getId() + "\n"
                        + " Nome: " + usuario.getNome() + "\n"
                        + " CPF: " + usuario.getCpf()+ "\n"
                        + " Data de nascimento: " + usuario.getData_nascimento() + "\n"
                        + " Altura: " + usuario.getAltura()
                );

            } else {
                lblResultado.setText("Usuario não encontrado");
            }
        } catch (SQLException erro) {
            lblResultado.setText("Erro ao buscar usuario: " + erro.getMessage());
        } catch (NumberFormatException erro) {
            lblResultado.setText("ID Inválido");
        }
    }//GEN-LAST:event_btnBuscaIDActionPerformed

    private void btnBuscaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaUsuariosActionPerformed
        // TODO add your handling code here:
        
      this.carregaTabelaUsuarios();

    }//GEN-LAST:event_btnBuscaUsuariosActionPerformed

    private void btnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlteracoesActionPerformed
        // TODO add your handling code here:
        
                CadastroDAO usuarioDAO = new CadastroDAO();
        
        try {
            //Percorre todas as linhas da tabela
            for(int i = 0; i < tblUsuarios.getRowCount(); i++){
                //Captura os valores editados da tabela
                int id = Integer.parseInt(tblUsuarios.getValueAt(i, 0).toString());
                String nome = tblUsuarios.getValueAt(i, 1).toString();
                String cpf = tblUsuarios.getValueAt(i, 2).toString();
                String dataNascimentoStr = tblUsuarios.getValueAt(i, 3).toString();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data_nascimento = null;

            // Faz o parse da data
            try {
                if (!dataNascimentoStr.isEmpty()) {
                    data_nascimento = sdf.parse(dataNascimentoStr); // Faz o parsing da data
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido na linha " + (i + 1));
                return; // Sai do método em caso de erro
            }              
                
                
                Float altura = Float.parseFloat(tblUsuarios.getValueAt(i, 4).toString());
                
                //cria um objeto com os valores atualizados
                Cadastro usuario = new Cadastro();
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setCpf(cpf);
                usuario.setData_nascimento(data_nascimento);
                usuario.setAltura(altura);
                
                //Atualiza o usuario no banco de dados
                
                usuarioDAO.atualizarUsuario(usuario);              
                
            }
            
            //Exibe mensagem de sucesso na alteração
            
            JOptionPane.showMessageDialog(this, "Usuario atualizado!");
            //Recarrega tabela com os dados atualizados
            
            carregaTabelaUsuarios();
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o usuario"+erro.getMessage());
        }
    }//GEN-LAST:event_btnSalvarAlteracoesActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        
        CadastroDAO usuarioDAO = new CadastroDAO();
        //Pega o ID digitado no campo txtExcluirID
        String idText = txtExcluirID.getText();
        if (idText.isEmpty()) {
            //Verifica se o campo esta vazio
            JOptionPane.showMessageDialog(this, "Por favor insira um ID valido");
            return;
            
        }
        try {
            //Converte o ID para inteiro
            int ID = Integer.parseInt(idText);
            //Chama o metodo de exclusão no DAO
            usuarioDAO.deletarUsuario(ID);
            JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso");
            //Limpa o campo txtExcluirID após exclusão
            txtExcluirID.setText("");
            
        } catch (NumberFormatException erro) {
            //trata caso o valor inserido não seja um numero valido
            JOptionPane.showMessageDialog(this,"ID invalido, insira um numero");
        }
        catch(SQLException erro){
            //Trata erros no banco de dados
            JOptionPane.showMessageDialog(this, "Erro ao excluir usuário"+erro.getMessage());
        
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtExcluirIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExcluirIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExcluirIDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscaID;
    private javax.swing.JButton btnBuscaUsuarios;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarAlteracoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtDataNascimento;
    private javax.swing.JTextField txtExcluirID;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}