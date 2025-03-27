import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    //banco de dados
    private BilheteUnico[] bilhete = new BilheteUnico[5];
    private int index = 0;

    //MENU PRINCIPAL
    public void menuPrincipal(){
        int opcao=0;
        String menu = "1.Administrador \n2.Usuário \n3.Finalizar";
        do{
           opcao =parseInt(showInputDialog(menu));
            if(opcao == 1){
                menuAdm();
            } else if (opcao == 2) {
                menuUsuario();
            }
        }while(opcao != 3);
    }

    //MENU ADM
    private void menuAdm(){
        int opcao=0;
        String menuAdmin="1.Emitir bilhete\n2.Lista bilhetes\n3.Excluir Bilhete\n4.Sair";
        do {
            opcao=parseInt(showInputDialog(menuAdmin));
            if(opcao<1 || opcao>4) {
                showMessageDialog(null, "Opção invalida");
            }else{
                if (opcao == 1) {
                    emitir();
                } else if (opcao == 2) {
                    listar();
                } else if (opcao == 3) {
                    excluir();
                }
            }
        }while(opcao !=4);
    }

    private void emitir(){
        String nome, perfil;
        long cpf;
        if(index < bilhete.length){
            nome = showInputDialog("Nome do Usuário: ");
            cpf = parseLong(showInputDialog("CPF: "));
            perfil= showInputDialog("Estudante ou Professor ou Comum:");
            bilhete[index]=new BilheteUnico(nome,cpf,perfil);
            index++;
        }else {
            showMessageDialog(null,"Procure um posto de atendimento");
        }
    }

    private void listar(){
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for(int i=0; i<index; i++){
           aux += "Numero do bilhete: "+ bilhete[i].numero +"\n"+"Saldo do bilhete: "+ df.format(bilhete[i].saldo)+"\n"+"Usuário: "+bilhete[i].usuario.nome+"\n"+"Perfil: "+bilhete[i].usuario.perfil+"\n"+"CPF: "+bilhete[i].usuario.cpf+"\n"+"\n";
        }
        showMessageDialog(null,aux);

    }

    private void excluir(){
        int resposta;
        int indice = pesquisar();
        if (indice != -1){
            resposta = showConfirmDialog(null,"Tem certeza que deseja excluir?");
            if(resposta == YES_OPTION) {
                bilhete[indice] = bilhete[index-1];
                index--;
            }
        }
    }

    //MENU USUARIO
    private void menuUsuario(){
        int opcao=0;
        String menu="1.Carregar Bilhete\n2.Consultar Saldo \n3.Passar na catraca\n4.Sair";
        do{
            opcao=parseInt(showInputDialog(menu));
            if(opcao<1 || opcao>4){
                showMessageDialog(null,"Opção invalida");
            }else {
                if (opcao == 1) {
                    carregarBilhete();
                }else if(opcao == 2){
                    consultarSaldo();
                }else if(opcao == 3){
                    catraca();
                }
            }
        }while(opcao!=4);
    }

    private int pesquisar(){
        long cpf=parseLong(showInputDialog("CPF: "));;

        for(int i=0;i<index;i++){
            if(cpf==bilhete[i].usuario.cpf) {
                return i;
            }
        }
        showMessageDialog(null, "CPF não encontrado");
        return -1;
    }

    private void carregarBilhete(){
        String menu="Insira o valor que deseja carregar:";
        int indice=pesquisar();
        double valor=0;
        if(indice != -1) {
            valor = parseDouble(showInputDialog(menu));
            bilhete[indice].carregar(valor);
        }

    }

    private void consultarSaldo(){
        int indice = pesquisar();
        if(indice != -1){
            showMessageDialog(null,"Saldo: R$ "+bilhete[indice].saldo);
        }

    }

    private void catraca(){
        int indice=pesquisar();
        if(indice != -1) {
            showMessageDialog(null,bilhete[indice].passarNaCatraca());
        }
    }
}
