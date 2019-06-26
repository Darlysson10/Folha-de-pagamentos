package Folhdadepagamento;
import java.util.*;

public class FolhaDePagamento {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int in = -1;
        int i, j,ids;
        int inputs = 0;
        int id = 0;
        int sexta2 = 0;
        String getString, in2;
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int diasemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        java.util.ArrayList<Empregado> empregados = new java.util.ArrayList<Empregado>();
        java.util.ArrayList<Empregado> backupEmpregado = new java.util.ArrayList<Empregado>();
        Stack<ArrayList<Empregado>> undo = new Stack<ArrayList<Empregado>>();
        Stack<ArrayList<Empregado>> redo = new Stack<ArrayList<Empregado>>();



        while (in != 0) {
            System.out.println("\nO que deseja fazer?\n\n1 - Adicionar empregado\n" +
                    "2 - Remover empregado\n3 - Lancar cartao de ponto\n4 - Lancar resultado de venda\n" +
                    "5 -  Lancar uma taxa de servico\n6 - Alterar detalhes de um empregado\n" +
                    "7 - Rodar a folha de pagamento para hoje\n8 - Desfazer/Refazer\n" +
                    "9 - Agenda de pagamentos\n10 - Criar nova agenda de pagamento\n0 - Encerrar");

            in = input.nextInt();
            getString = input.nextLine();
            if (in > 0 && in <= 7) {

                if (inputs > 0) {

                    for (j = 0; j < id; j++) {
                       backupEmpregado.add(empregados.get(j));


                    }

                    undo.push(backupEmpregado);
                }
                //if (id > 1) backupEmpregado.remove(id+1);
                inputs++;
            }

            switch (in) {
                case 1:
                    //addempregado
                    System.out.println("Digite o tipo de empregado:\n horista,  assalariado ou  comissionado");
                    in2 = input2.nextLine();
                    if (in2.equals("horista")) {
                        Horista employee = new Horista();
                        employee.addEmpregado(employee,id);
                        empregados.add(employee);
                    } else if (in2.equals("assalariado")) {
                        Assalariado employee = new Assalariado();
                        employee.addEmpregado(employee,id);
                        empregados.add(employee);
                    } else if (in2.equals("comissionado")) {
                        Comissionado employee = new Comissionado();
                        employee.addEmpregado(employee,id);
                        empregados.add(employee);
                    } else {
                        System.out.println("Entrada inválida\nDigite conforme foi mostrado.");
                        break;
                    }
                    /*backupEmpregado.add(id,empregados.get(id));
                    undo.push(backupEmpregado);
                    System.out.println(backupEmpregado.get(id).toString());*/
                    id++;
                    break;
                case 2:
                    System.out.println("Digite o id do empregado que desejas remover");
                    int idremove = input.nextInt();
                   // backupEmpregado.set(idremove,empregados.get(idremove));
                    /*System.out.println(backupEmpregado.get(idremove).toString());*/
                    System.out.println("Removendo "+empregados.get(idremove).getNome());
                    empregados.remove(idremove);
                    System.out.println("Operação concluída");
                    id--;
                    break;
                case 3:
                    System.out.println("Digite o número de identificação do empregado:");
                    ids = input.nextInt();
                    getString = input.nextLine();
                    if (empregados.get(ids).getTipo().equals("horista"))
                    {
                        empregados.get(ids).cartaoPonto(empregados.get(ids));
                    }
                    else System.out.println("Empregado não é horista");
                    break;
                case 4:
                    System.out.println("Digite o numero de identificacao do empregado:");
                    ids = input.nextInt();
                    getString = input.nextLine();
                    if (empregados.get(ids).getTipo().equals("comissionado"))
                    {
                        empregados.get(ids).vendas(empregados.get(ids));
                    }
                    else System.out.println("O empregado nao e comissionado");
                    break;
                case 5:
                    System.out.println("Digite o numero de identificacao do empregado:");
                    ids = input.nextInt();
                    if (empregados.get(ids).isSindicato())
                    {
                        System.out.print("Digite a taxa de servico: ");
                        empregados.get(ids).setTaxaDeServico(input.nextDouble());
                        System.out.println("Feito");
                    }
                    else System.out.println("Empregado não pertence a um sindicato");
                    break;
                case 6:
                    System.out.println("Digite o numero de identificacao do empregado:");
                    ids = input.nextInt();
                    empregados.get(ids).editarEmpregado(empregados.get(ids));
                    break;
                case 7:
                    System.out.println("Data: " + dia + "/" + mes + "/" + ano);
                    System.out.println();
                    int flag = 0;

                    if (dia == 29 && diasemana == 6) {
                        flag = 1;
                    } else if (dia == 28 && diasemana == 6) {
                        flag = 1;
                    } else if (dia == 30 && diasemana != 7 && diasemana != 1) flag = 1;

                    for (j = 0; j < empregados.size(); j++)
                    {


                        if(empregados.get(j).getTipo().equals("horista") && diasemana == 6) empregados.get(j).rodarFolha(empregados.get(j));
                        else if (empregados.get(j).getTipo().equals("assalariado") && flag == 1) empregados.get(j).rodarFolha(empregados.get(j));
                        else if (empregados.get(j).getTipo().equals("comissionado") && diasemana == 6 && ((sexta2%2 == 0) || (sexta2 == 0))) empregados.get(j).rodarFolha(empregados.get(j));

                        dia++;
                        diasemana++;
                        if (dia == 31 && mes != 12)
                        {
                            dia = 1;
                            mes++;
                        }
                        if (diasemana == 8) diasemana = 1;
                        if (dia == 31 && mes == 12)
                        {
                            ano++;
                            mes = 1;
                            dia = 1;
                        }
                        if (diasemana == 6) sexta2++;

                     }
                    break;
                case 8:
                    int action;
                    System.out.println("Pressione 1 para desfazer ou 2 para refazer");
                    action = input.nextInt();
                    if (inputs > 0)
                    {
                        if (action == 1)
                        {
                            redo.push(empregados);
                            empregados = undo.pop();
                            System.out.println("Desfeito");
                        }
                        else
                        {
                            undo.push(empregados);
                            empregados = redo.pop();
                            System.out.println("Refeito");
                        }
                    }
                    break;

                case 9:
                    System.out.println("Agenda de pagamento\n");
                    for (j = 0; j < empregados.size(); j++)
                    {
                        empregados.get(j).novaAgenda(empregados.get(j));

                    }
                    break;
                case 10:
                    System.out.println("Digite o número de identificação do empregado");
                    String freq1,freq2;
                    int freq3;
                    ids = input.nextInt();
                    getString = input.nextLine();
                    System.out.println("Digite a frequência de pagamento: Mensal ou Semanal.\n" +
                            "Se for mensal, digite o dia do pagamento.\n" +
                            "Se for semanal, informe o intervalo de semanas de pagamento e o dia da semana para ser pago." +
                            "\nO último dia útil do mês é considerado como $.");
                    System.out.println("Por exemplo, se for mensal, digite: mensal 1 (significa que sera pago todo dia 1 do mes).\n" +
                            "Se for semanal, digite: semanal 2 sexta(significa que sera pago a cada 2 semanas na sexta)\n\n");
                    System.out.println("Digite se é mensal ou semanal");
                    freq1 = input.nextLine();

                    System.out.println("Digite a frequência de pagamento (inteiro)");
                    freq3 = input.nextInt();
                    if (freq1.equals("semanal")){
                        System.out.println("Digite o dia da semana de pagamento");
                        freq2 = input.nextLine();
                        empregados.get(ids).setNovaAgenda("semanal");
                        empregados.get(ids).setFreq(freq3);
                        empregados.get(ids).setDia(freq2);
                    }
                    else if (freq1.equals("mensal"))
                    {
                        empregados.get(ids).setNovaAgenda("mensal");
                        empregados.get(ids).setFreq(freq3);
                    }












            }




    }
  }

}
