/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.view;

/**
 *
 * @author Davi
 */
public class SolicitacaoReposicaoView extends javax.swing.JFrame {
    
    // Controller usado para acessar as solicitações de reposição.
    private br.com.ifba.prg03projeto.mercado.controller.SolicitacaoReposicaoController solicitacaoController;

    // Controller usado para buscar as filiais.
    private br.com.ifba.prg03projeto.mercado.controller.FilialController filialController;

    // Controller usado para buscar os produtos.
    private br.com.ifba.prg03projeto.mercado.produto.controller.ProdutoController produtoController;

    // Solicitação selecionada durante uma edição.
    private br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao solicitacaoEdicao;

    // Lista auxiliar usada pelo combo de filiais.
    private java.util.List<br.com.ifba.prg03projeto.mercado.entity.Filial> listaFiliais;

    // Lista auxiliar usada pelo combo de produtos.
    private java.util.List<br.com.ifba.prg03projeto.mercado.produto.entity.Produto> listaProdutos;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SolicitacaoReposicaoView.class.getName());

    /**
     * Creates new form SolicitacaoReposicaoView
     */
    
    // Limpa os campos e encerra o modo de edição.
    private void limparCampos() {

        // Seleciona o primeiro item dos combos, caso existam dados.
        if (cboFilial.getItemCount() > 0) {
            cboFilial.setSelectedIndex(0);
        }

        if (cboProduto.getItemCount() > 0) {
            cboProduto.setSelectedIndex(0);
        }

        // Volta o status para PENDENTE.
        cboStatus.setSelectedItem("PENDENTE");

        // Limpa a quantidade.
        txtQuantidadeSolicitada.setText("");

        // Encerra o modo de edição.
        solicitacaoEdicao = null;

        // Coloca o cursor no campo quantidade.
        txtQuantidadeSolicitada.requestFocus();
    }
    
    // Carrega todas as solicitações cadastradas na tabela.
    private void carregarTabela() {

        // Obtém o modelo da tabela.
        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tblSolicitacoes.getModel();

        // Limpa as linhas antigas.
        modelo.setRowCount(0);

        // Busca todas as solicitações.
        java.util.List<br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao> lista =
            solicitacaoController.listarTodos();

        // Percorre as solicitações encontradas.
        for (br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao solicitacao : lista) {

            // Adiciona cada solicitação como uma linha da tabela.
            modelo.addRow(new Object[]{
                solicitacao.getId(),
                solicitacao.getDataSolicitacao(),
                solicitacao.getFilial().getNome(),
                solicitacao.getProduto().getNome(),
                solicitacao.getQuantidadeSolicitada(),
                solicitacao.getStatus()
            });
        }
    }
    
    // Carrega filiais e produtos nos campos de seleção.
    private void carregarCombos() {

        // Remove os itens padrões do NetBeans.
        cboFilial.removeAllItems();
        cboProduto.removeAllItems();

        // Busca todas as filiais.
        listaFiliais = filialController.listarTodos();

        // Adiciona os nomes das filiais no combo.
        for (br.com.ifba.prg03projeto.mercado.entity.Filial filial : listaFiliais) {
        cboFilial.addItem(filial.getNome());
        }

        // Busca todos os produtos.
        listaProdutos = produtoController.findAll();

        // Adiciona os nomes dos produtos no combo.
        for (br.com.ifba.prg03projeto.mercado.produto.entity.Produto produto : listaProdutos) {
        cboProduto.addItem(produto.getNome());
        }
    }
    
    public SolicitacaoReposicaoView() {
        // Inicializa os componentes visuais.
        initComponents();

        // Busca os controllers dentro do contexto do Spring.
        solicitacaoController =
            br.com.ifba.prg03projeto.mercado.Prg03sistemaMercadoApplication.contexto
                    .getBean(br.com.ifba.prg03projeto.mercado.controller.SolicitacaoReposicaoController.class);

        filialController =
            br.com.ifba.prg03projeto.mercado.Prg03sistemaMercadoApplication.contexto
                    .getBean(br.com.ifba.prg03projeto.mercado.controller.FilialController.class);

        produtoController =
            br.com.ifba.prg03projeto.mercado.Prg03sistemaMercadoApplication.contexto
                    .getBean(br.com.ifba.prg03projeto.mercado.produto.controller.ProdutoController.class);

        // Inicialmente nenhuma solicitação está sendo editada.
        solicitacaoEdicao = null;

        // Carrega os dados da tela.
        carregarCombos();
        carregarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        cboFilial = new javax.swing.JComboBox<>();
        cboProduto = new javax.swing.JComboBox<>();
        lblQuantidadeReposicao = new javax.swing.JLabel();
        txtQuantidadeSolicitada = new javax.swing.JTextField();
        cboStatus = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSolicitacoes = new javax.swing.JTable();
        sepFormularioReposicao = new javax.swing.JSeparator();
        btnAtualizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTitulo.setText("GERENCIAMENTO DE SOLICITAÇÕES DE REPOSIÇÃO");

        cboFilial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblQuantidadeReposicao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantidadeReposicao.setText("Quantidade:");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDENTE", "APROVADA", "RECUSADA", "CONCLUIDA" }));
        cboStatus.addActionListener(this::cboStatusActionPerformed);

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        tblSolicitacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Data", "Filial", "Produto", "Quantidade", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSolicitacoes);

        btnAtualizar.setText("ATUALIZAR");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnRemover.setText("REMOVER");
        btnRemover.addActionListener(this::btnRemoverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepFormularioReposicao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboFilial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblQuantidadeReposicao)
                                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnSalvar)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLimpar))
                                            .addComponent(txtQuantidadeSolicitada, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(btnAtualizar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemover)))
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(cboFilial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeReposicao)
                    .addComponent(txtQuantidadeSolicitada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpar)
                    .addComponent(btnSalvar))
                .addGap(36, 36, 36)
                .addComponent(sepFormularioReposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar)
                    .addComponent(btnEditar)
                    .addComponent(btnRemover))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboStatusActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        
    // Obtém a linha selecionada.
    int linha = tblSolicitacoes.getSelectedRow();

    // Verifica se alguma solicitação foi selecionada.
    if (linha >= 0) {

        // Solicita a confirmação do usuário.
        int confirmacao = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover esta solicitação?",
                "Confirmação",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        // Verifica se a remoção foi confirmada.
        if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {

            // Obtém o ID da solicitação selecionada.
            Long id = Long.parseLong(
                    tblSolicitacoes.getValueAt(linha, 0).toString()
            );

            try {

                // Remove a solicitação.
                solicitacaoController.deletar(id);

                // Atualiza a tabela.
                carregarTabela();

                // Limpa o formulário.
                limparCampos();

                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Solicitação removida com sucesso!",
                        "Sucesso",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {

                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Erro ao remover solicitação: " + e.getMessage(),
                        "Erro",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }

    } else {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione uma solicitação para remover!",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
    }
        
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
    
        try {

        // Verifica se existem filiais cadastradas.
        if (listaFiliais == null || listaFiliais.isEmpty()) {
            throw new RuntimeException(
                    "Cadastre pelo menos uma filial antes de criar uma solicitação."
            );
        }

        // Verifica se existem produtos cadastrados.
        if (listaProdutos == null || listaProdutos.isEmpty()) {
            throw new RuntimeException(
                    "Cadastre pelo menos um produto antes de criar uma solicitação."
            );
        }

        // Verifica se a quantidade foi preenchida.
        if (txtQuantidadeSolicitada.getText().isBlank()) {
            throw new RuntimeException(
                    "A quantidade solicitada é obrigatória."
            );
        }

        // Converte a quantidade digitada para inteiro.
        Integer quantidade =
                Integer.parseInt(txtQuantidadeSolicitada.getText().trim());

        // Impede valores iguais ou menores que zero.
        if (quantidade <= 0) {
            throw new RuntimeException(
                    "A quantidade solicitada deve ser maior que zero."
            );
        }

        // Obtém os índices selecionados.
        int indiceFilial = cboFilial.getSelectedIndex();
        int indiceProduto = cboProduto.getSelectedIndex();

        // Obtém a filial selecionada.
        br.com.ifba.prg03projeto.mercado.entity.Filial filialSelecionada =
                listaFiliais.get(indiceFilial);

        // Obtém o produto selecionado.
        br.com.ifba.prg03projeto.mercado.produto.entity.Produto produtoSelecionado =
                listaProdutos.get(indiceProduto);

        // Cria uma solicitação nova ou usa a solicitação em edição.
        br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao solicitacao;

        if (solicitacaoEdicao == null) {

            // Cria uma nova solicitação.
            solicitacao =
                    new br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao();

            // Define a data e o horário somente no cadastro.
            solicitacao.setDataSolicitacao(
                    java.time.LocalDateTime.now()
            );

        } else {

            // Usa a solicitação existente durante a edição.
            solicitacao = solicitacaoEdicao;
        }

        // Preenche os dados da solicitação.
        solicitacao.setFilial(filialSelecionada);
        solicitacao.setProduto(produtoSelecionado);
        solicitacao.setQuantidadeSolicitada(quantidade);
        solicitacao.setStatus(cboStatus.getSelectedItem().toString());

        // Salva a solicitação.
        solicitacaoController.salvar(solicitacao);

        // Atualiza a tabela.
        carregarTabela();

        // Limpa os campos.
        limparCampos();

        // Exibe mensagem de sucesso.
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Solicitação salva com sucesso!",
                "Sucesso",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

    } catch (NumberFormatException e) {

        // Exibe erro caso a quantidade não seja um número inteiro.
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "A quantidade deve ser um número inteiro válido!",
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        // Exibe qualquer outro erro.
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar solicitação: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
    
    // Atualiza os combos e a tabela.
    carregarCombos();
    carregarTabela();

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Solicitações atualizadas com sucesso!",
            "Informação",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
    );    
        
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    
        // Obtém a linha selecionada na tabela.
    int linha = tblSolicitacoes.getSelectedRow();

    // Verifica se alguma solicitação foi selecionada.
    if (linha >= 0) {

        // Obtém o ID da solicitação.
        Long id = Long.parseLong(
                tblSolicitacoes.getValueAt(linha, 0).toString()
        );

        // Busca a solicitação completa pelo ID.
        org.springframework.http.ResponseEntity<br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao> resposta =
                solicitacaoController.buscarPorId(id);

        // Pega a solicitação retornada.
        br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao solicitacao =
                resposta.getBody();

        // Verifica se foi encontrada.
        if (solicitacao != null) {

            // Guarda a solicitação em edição.
            solicitacaoEdicao = solicitacao;

            // Preenche a quantidade.
            txtQuantidadeSolicitada.setText(
                    String.valueOf(solicitacao.getQuantidadeSolicitada())
            );

            // Seleciona o status.
            cboStatus.setSelectedItem(
                    solicitacao.getStatus()
            );

            // Seleciona a filial correspondente.
            for (int i = 0; i < listaFiliais.size(); i++) {

                if (listaFiliais.get(i).getId()
                        .equals(solicitacao.getFilial().getId())) {

                    cboFilial.setSelectedIndex(i);
                    break;
                }
            }

            // Seleciona o produto correspondente.
            for (int i = 0; i < listaProdutos.size(); i++) {

                if (listaProdutos.get(i).getId()
                        .equals(solicitacao.getProduto().getId())) {

                    cboProduto.setSelectedIndex(i);
                    break;
                }
            }

            // Coloca o cursor no campo quantidade.
            txtQuantidadeSolicitada.requestFocus();

        } else {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Solicitação não encontrada!",
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }

    } else {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione uma solicitação para editar!",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
      }
        
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
    
    // Limpa os campos do formulário.
    limparCampos();      
        
    }//GEN-LAST:event_btnLimparActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SolicitacaoReposicaoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboFilial;
    private javax.swing.JComboBox<String> cboProduto;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuantidadeReposicao;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSeparator sepFormularioReposicao;
    private javax.swing.JTable tblSolicitacoes;
    private javax.swing.JTextField txtQuantidadeSolicitada;
    // End of variables declaration//GEN-END:variables
}
