import java.util.ArrayList;
import javax.swing.JOptionPane;


public class JavaMySqlApp {

    public static void main(String args[]) 
    {   String  sMenu[] = {"Incluir", "Consultar", "Alterar", "Excluir", "Listar", "Sair"};
        ArrayList<Cliente> aLista;
        ArrayList<Pedido> aListapedido;
        Cliente    uDraft  = new Cliente();
        Pedido pedido = new Pedido();
       
        Gui     gMenu   = new Gui(sMenu);
        CrudBD  cBD     = new CrudBD();
        int     iOption;

        
        do
        {   uDraft.clearObject();
            iOption = gMenu.getOption();
            switch(iOption)
            {  case 0: // Incluir
               
                uDraft.setNome(JOptionPane.showInputDialog(null, "Digite o Usuário:", "Incluir", JOptionPane.QUESTION_MESSAGE));
                uDraft.setTelefone(JOptionPane.showInputDialog(null, "Digite o Telefone:", "Incluir", JOptionPane.QUESTION_MESSAGE));
                uDraft.setEndereco(JOptionPane.showInputDialog(null, "Digite o Endereço:", "Incluir", JOptionPane.QUESTION_MESSAGE));
                cBD.incluirRegCliente(uDraft);
                cBD.consultarRegCliente(uDraft);
              
                pedido.setNome(JOptionPane.showInputDialog(null, "Digite o Pedido:", "Incluir", JOptionPane.QUESTION_MESSAGE));
                pedido.setPreco(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Preco:", "Incluir", JOptionPane.QUESTION_MESSAGE)));
                cBD.incluirReg(pedido, uDraft); 
                cBD.consultarReg(pedido); 

                JOptionPane.showMessageDialog(null, "cliente: " + uDraft.getNome() + "\n" + "Telefone: " + uDraft.getTelefone() + "\n" + "Endereco:" + uDraft.getEndereco());
                JOptionPane.showMessageDialog(null, "pedido: " + pedido.getNome() + "\n" + "preco: " + pedido.getPreco());
                break;
                                            
                case 1: // Consultar
                    uDraft.setNome(JOptionPane.showInputDialog(null, "Digite o Usuário:", "Consultar", JOptionPane.QUESTION_MESSAGE));
                    cBD.consultarRegCliente(uDraft);
                    
                    pedido.setNome(JOptionPane.showInputDialog(null, "Digite o Pedido:", "Consultar", JOptionPane.QUESTION_MESSAGE));
                    cBD.consultarReg(pedido);

                    String pedidocliente;

                    pedidocliente = "Pedido para o cliente:\n" + "\n" +
                    "Nome: " + uDraft.getNome() + "\n" +
                    "Telefone: " + uDraft.getTelefone() + "\n" +
                    "Endereço: " + uDraft.getEndereco() + "\n" +
                    "Pedido: " + pedido.getNome() + "\n" +
                    "Valor: R$ " + pedido.getPreco();

                    JOptionPane.showMessageDialog(null, pedidocliente);
                    break;
                            
                case 2: // Alterar
                    String nomeAtual = JOptionPane.showInputDialog(null, "Digite o nome do Usuário atual:", "Alterar", JOptionPane.QUESTION_MESSAGE);
                    
                    
                    uDraft.setNome(JOptionPane.showInputDialog(null, "Digite o novo nome do Usuário:", "Alterar", JOptionPane.QUESTION_MESSAGE));
                    uDraft.setTelefone(JOptionPane.showInputDialog(null, "Digite o Telefone:", "Alterar", JOptionPane.QUESTION_MESSAGE));
                    uDraft.setEndereco(JOptionPane.showInputDialog(null, "Digite o Endereço:", "Alterar", JOptionPane.QUESTION_MESSAGE));
                    cBD.alterarRegCliente(uDraft, nomeAtual);
                    cBD.consultarRegCliente(uDraft);
                    
                    String nomeAtualpedido = JOptionPane.showInputDialog(null, "Digite o nome do pedido atual:", "Alterar", JOptionPane.QUESTION_MESSAGE);
                    pedido.setNome(JOptionPane.showInputDialog(null, "Digite o Pedido:", "Alterar", JOptionPane.QUESTION_MESSAGE));
                    pedido.setPreco(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Preco:", "Alterar", JOptionPane.QUESTION_MESSAGE)));
                    cBD.alterarReg(pedido, nomeAtualpedido);
                    cBD.consultarReg(pedido);
                
                    break;
                            
                case 3: // Excluir
                    // uDraft.setNome(JOptionPane.showInputDialog(null, "Digite o Usuário:", "Excluir", JOptionPane.QUESTION_MESSAGE));
                    // cBD.excluirRegCliente(uDraft);
                    // cBD.consultarRegCliente(uDraft);

                    pedido.setNome(JOptionPane.showInputDialog(null, "Digite o pedido:", "Excluir", JOptionPane.QUESTION_MESSAGE));
                    cBD.excluirReg(pedido);
                    cBD.consultarReg(pedido);

                    break;
                            
                case 4: // Listar
                    JOptionPane.showMessageDialog(null, "Acione OK para ver no console os clientes e pedidos cadastrados!");
                    aLista = cBD.buscarTodos(); 
                    aListapedido = cBD.buscarTodosPedidos(); 
                    String msn = "";
                    String msn1 = "";
                  
                    System.out.println("--------- Clientes ---------");
                    for (int i = 0; i < aLista.size(); i++) {
                        uDraft = aLista.get(i);

                        msn = "Id: " + uDraft.getId() + " - " +
                        "Nome: " + uDraft.getNome()  + " - " +
                        "Telefone: " + uDraft.getTelefone() + " - " +
                        "Endereco: " + uDraft.getEndereco();  
                        
                        System.out.println(msn);
                    }
                    System.out.println("---------------------------");
                    System.out.print("\n");
                    System.out.println("--------- Pedidos ---------");
                    for (int i = 0; i < aListapedido.size(); i++) {
                        pedido = aListapedido.get(i);

                        msn1 = "Id: " + pedido.getId() + " - " +
                        "Nome: " + pedido.getNome()  + " - " +
                        "Preco: " + "R$" + pedido.getPreco() + " - " +
                        "id_cliente: " + pedido.getIdCliente();  
                        
                        System.out.println(msn1);
                    }
                    System.out.println("---------------------------");

                    //JOptionPane.showMessageDialog(null, msn); 
                    break;
                            
                case 5: // Sair
                    System.exit(0);
                    break;
            }
        }while(iOption < sMenu.length-1);
    }
}