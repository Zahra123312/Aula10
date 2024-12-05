import java.util.List;

public final class Cliente
{   private int  id;
    private String nome;
    private String  telefone;
    private String  endereco;
    private List<Pedido> pedidos;
    
    public  Cliente()
    {   clearObject();
    }
    public  Cliente(int id, String nome, String telefone, String endereco)
    {  
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    public  int  getId(){
        return  id;
    }
    public  void setId(int id){
        this.id = id;
    }  
    
    public  String getNome()
    {   return  nome;
    }

    public  void setNome(String nome)
    {   this.nome = nome;
    }

    public  String getTelefone()
    {   return  telefone;
    }

    public  void setTelefone(String telefone)
    {   this.telefone = telefone;
    }

    public  String getEndereco()
    {   return  endereco;
    }

    public  void setEndereco(String endereco)
    {   this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public  void  clearObject()
    {   nome = "Não há usuário cadastrado";
    }
}