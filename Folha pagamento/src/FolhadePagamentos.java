/*
    Detalhes sobre o array de dados
    0 - tipo de empregado
    1 - salario
    2 - percentual de comissao
    3 - metodo de pagamento
    4 - sindicato
    5 - taxa sindical
    6 - horas trabalhadas
    7 - hora extra
    8 - total de vendas (em dinheiro)
    9 - taxa de servico
    10 - numero no sindicato
    11 - flag de remocao
    12 - nova agenda


*/
import java.util.Scanner;
import java.util.Calendar;
import java.util.*;

public class FolhadePagamentos
{
    public static void main (String[] args)
    {
        String[] nomes;
        String[] endereco;
        Double[][] dados;
        
        Double[][] dados_backup;
        String[] nomes_backup;
        String[] endereco_backup;

        dados = new Double[100][15];
        nomes = new String[100];
        endereco = new String[100];
       //Backup para funcao de alterar dados do empregado
        nomes_backup = new String[100];
        dados_backup = new Double[100][15];
        endereco_backup = new String[100];
        
        String pularlinha;

       //Backup para salvar os dados antes do undo
        String[] nomes_antes_undo = new String[100];
        String[] endereco_antes_undo = new String[100];
        Double[][] dados_antes_undo = new Double[100][15];

        int sexta2 = 0;
        
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH), mes = Calendar.getInstance().get(Calendar.MONTH) + 1, ano = Calendar.getInstance().get(Calendar.YEAR);
        int diasemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        int numfuncionarios = 0;
        
        int[] input_anterior = new int[200]; //auxiliar na funcao undo e redo
        int[] input_undo = new int[200];
        int inputs = 0;

        int undo = 0;
        int redo = 0;
        
        int usuario = -1 ,id2 = - 1,id3 = -1 ,id4 = -1 ,id5 = -1;
        //arrays de ids salvos para funcao undo e redo
        int[] ids0 = new int[100];
        int[] ids1 = new int[100];
        int[] ids2 = new int[100];
        int[] ids3 = new int[100];
        int[] ids4 = new int[100];
        int[] ids5 = new int[100];
        
        //Indices dos arrays de ids para funcao undo e redo. Eles servem para casos em que ha mais de um undo em funcoes que foram feitas duas vezes seguidas. Ex: quando solicitamos para alterar o dados suas vezes seguidas.
        int i0 = 0,i1 = 0, i2 = 0, i3 = 0,i4 = 0, i5 = 0;

        //arrays para salvar os dados antes de fazer o undo -- principalmente para desfazer a funcao 6
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);
        
        int menus = -1;
       
        int i;
        for (i = 0; i<100; i++) dados[i][11] = 1.0;
        int id = 0;
        while (menus != 0) 
        {
            System.out.println("\nO que deseja fazer?\n\n1 - Adicionar empregado\n2 - Remover empregado\n3 - Lancar cartao de ponto\n4 - Lancar resultado de venda\n5 -  Lancar uma taxa de servico\n6 - Alterar detalhes de um empregado\n7 - Rodar a folha de pagamento para hoje\n8 - Desfazer/Refazer\n9 - Agenda de pagamentos\n10 - Criar nova agenda de pagamento\n0 - Encerrar");

            menus = menu.nextInt();
            pularlinha = menu.nextLine();
            
            if (menus == 0) System.out.println("Encerrado");
            if (menus == 1) 
            {
                
                while (dados[id][11] != -1.0)
                {
                    if (dados[id][11] == 1.0)
                    {
                
                    	if (inputs > 0) 
                    	{
                    		System.out.println("\nEnter."); 
                    		pularlinha = input2.nextLine();
                    	}
                    		
                        System.out.println("Digite o nome do empregado");
                        nomes[id] = input2.nextLine();
                    
                        System.out.println("Digite o endereço do empregado:");
                        endereco[id] = input2.nextLine();

                        for (i=0;i<6;i++)
                        {
                            
                            if (i == 0) System.out.println("Digite o tipo de empregado - 1 horista, 2 assalariado, 3 comissionado");
                            if (i == 1)  System.out.println("Digite o salario do empregado associado ao tipo ja digitado");
                            if (i == 2)
                            {
                                if (dados[id][0] == 1.0)
                                {
                                    dados[id][6] = 0.0;
                                    dados[id][7] = 0.0;
                                } 
                                if (dados[id][0] == 3.0)
                                {
                                    System.out.println("Digite o perctentual de comissao:");
                                    dados[id][i] = input.nextDouble();
                                    dados[id][8] = 0.0;
                                }
                                else dados[id][i] = 0.0;
                            }
                            if (i == 3) System.out.println("Digite o metodo de pagamento: 1 - Cheque pelo correio 2 - Cheque em mao 3 - Deposito bancario");
                            if (i == 4) System.out.println("O empregado participa de um sindicato? 1 - Sim | 2 - Nao");
                            if (i == 5)
                            {
                                if (dados[id][4] == 1.0)
                                {
                                    dados[id][10] = (double)id;
                                    dados[id][9] = 0.0;
                                    System.out.println("Digite a taxa sindical:");
                                    dados[id][i] = input.nextDouble();

                                }
                                else dados[id][i] = 0.0;
                            }
                            
                            if (i!= 2 && i != 5) dados[id][i] = input.nextDouble();
                            
                            
                            
                        }
                    
                        System.out.printf("Empregado "+nomes[id] + " adicionado!" + "\nNumero de identificacao: "+id + "\n");
                        dados[id][11] = 0.0;
                        dados[id][12] = 0.0;
                        ids0[i0] = id;
                        i0++;
                        id++;
                        numfuncionarios++;
                        break;
                        
                    }
                    else id++;

                }
                
                input_anterior[inputs] = menus;
                inputs++;
               

            }
            if (menus == 2)
            {
                
                System.out.println("Digite o numero do empregado que desejas remover:");
                usuario = input.nextInt();
                pularlinha = input.nextLine();
                ids1[i1] = usuario;
                if (dados[usuario][11] == 0.0)
                {
                    dados[usuario][11] = 1.0;
                    if (input_anterior[inputs] == 2)
                    {
                        if (usuario <= id)
                        {
                            id = usuario;
                        }
                    }
                    else id = usuario;
                     numfuncionarios--;
                    System.out.printf("Funcionario %d removido\n",usuario);
                }
                else
                {
                    System.out.println("Empregado "+usuario + "nao existe");
                }
                
                input_anterior[inputs] = menus;
                inputs++;
                i1++;
                i++;

            }
            
            if (menus == 3)
            {
                
                
                double in_h,in_min, out_h, out_min, horas_trabalhadas;
                
                System.out.println("Digite o numero de identificacao do empregado:");
                id2 = input.nextInt();
                pularlinha = input.nextLine();
                ids2[i2] = id2;
                if(dados[id2][0] == 1)
                {
                    System.out.println("Digite a hora e minutos de entrada do empregado\nPrimeiro insira a hora e pressione ENTER, depois faça o mesmo com os minutos");
                    in_h = input.nextDouble();
                    in_min = input.nextDouble();

                    System.out.println("Digite a hora e minutos de saida do empregado\nPrimeiro insira a hora e pressione ENTER, depois faça o mesmo com os minutos");
                    out_h = input.nextDouble();
                    out_min = input.nextDouble();

                    if (out_h > in_h)
                    {
                        horas_trabalhadas = (out_h - in_h) + ((out_min/60) - (in_min/60));
                        if(horas_trabalhadas <= 8) dados[id2][6] += horas_trabalhadas;
                        else
                        {
                            dados[id2][6] += 8;
                            dados[id2][7] += (horas_trabalhadas - 8);

                        }
                        System.out.println("     -----Cartao de Ponto-----");
                        System.out.println("Empregado: "+nomes[id2] + "\nEntrada: "+(int)in_h + ":" +(int)in_min + "\nSaida: "+(int)out_h + ":"+(int)out_min + "\nHoras trabalhadas: "+(int)horas_trabalhadas); 
                        
                    }
                    else System.out.println("Entradas inválidas");

                }
                else System.out.println("Empregado nao e horista");
                input_anterior[inputs] = menus;
                inputs++;
                i2++;

            }

            if (menus == 4)
            {
               
                System.out.println("Digite o numero de identificacao do empregado:");
                int diavenda,mesvenda,anovenda;
                id3 = input.nextInt();
                pularlinha = input.nextLine();
                ids3[i3] = id3;
                if (dados[id3][0] == 3)
                {
                    System.out.println("Informe a data da venda:");
                    System.out.print("Dia: ");
                    diavenda = input.nextInt();
                    System.out.print("Mes: ");
                    mesvenda = input.nextInt();
                    System.out.print("Ano: ");
                    anovenda = input.nextInt();
                    System.out.println("Informe o valor da venda: ");
                    double solds  = input.nextDouble();
                    dados[id3][8] += solds;

                    System.out.println("Resultado da venda:\n" + "Empregado: "+nomes[id3] + "\nData da venda: "+diavenda + "/"+mesvenda + "/"+anovenda +"\nValor da venda: R$"+solds);
                }
                else System.out.println("O empregado nao e comissionado");
                input_anterior[inputs] = menus;
                inputs++;
                i3++;
            }

            if (menus == 5)
            {
                
                double taxa_servico;
                System.out.println("Digite o numero de identificacao do empregado:");
                id4 = input.nextInt();
                pularlinha = input.nextLine();
                ids4[i4] = id4;
                if (dados[id4][4] == 1)
                {   
                    System.out.print("Digite a taxa de servico: ");
                    taxa_servico = input.nextDouble();
                    dados[id4][9] = taxa_servico;

                    System.out.println("Feito.");

                }
                else System.out.println("Empregado nao pertence a um sindicato");
                input_anterior[inputs] = menus;
                inputs++;
                id4++;
            }

           if (menus == 6)
           {
                
                
                System.out.println("Digite o numero de identificacao do empregado:");
                
                id5 = input.nextInt();
                pularlinha = input.nextLine();
                ids5[i5] = id5;

                
                
                //NOME
                
                
                    System.out.print("Digite o nome do empregado: ");
                    nomes_backup[id5] = nomes[id5];
                    nomes[id5] = input2.nextLine();
                
              
                
                //ENDERECO
                
               
                    System.out.print("Digite o endereco do empregado: ");
                    endereco_backup[id5] = endereco[id5];
                    endereco[id5] = input2.nextLine();
                
                

                //TIPO
           
                   
                    System.out.println("Digite o tipo de empregado - 1 horista, 2 assalariado, 3 comissionado");
                    dados_backup[id5][0] = dados[id5][0];
                    dados[id5][0] = input.nextDouble();
                    System.out.println("Digite o salario do empregado associado ao tipo ja digitado");
                    dados_backup[id5][1] = dados[id5][1];
                    dados[id5][1] = input.nextDouble();
                    if (dados[id5][0] == 3)
                    {
                        System.out.println("Digite o perctentual de comissao:");
                        dados_backup[id5][2] = dados[id5][2];
                        dados[id5][2] = input.nextDouble();
                        dados_backup[id5][8] = dados[id5][8];
                        dados[id5][8] = 0.0;
                    }
                
               

                //METODO DE PAGAMENTO
               
                
                    System.out.println("Digite o metodo de pagamento: 1 - Cheque pelo correio 2 - Cheque em mao 3 - Deposito bancario");
                    dados_backup[id5][3] = dados[id5][3];
                    dados[id5][3] = input2.nextDouble();
                
                

                //SINDICATO
               
                    System.out.println("O empregado participa de um sindicato? 1 - Sim | 2 - Nao");
                    dados_backup[id5][4] = dados[id5][4];
                    dados[id5][4] = input2.nextDouble();
                    if (dados[id5][4] == 1.0)
                    {
                        dados_backup[id5][10] = dados[id5][10];
                        dados[id5][10] = (double)id5;
                        System.out.println("Digite a taxa sindical:");
                        dados_backup[id5][5] = dados[id5][5];
                        dados[id5][5] = input.nextDouble();

                    }
                    else
                    {
                        dados_backup[id5][5] = dados[id5][5];
                        dados[id][5] = 0.0;
                    } 
                    
               

                System.out.println("Dados do empregado alterados com sucesso");
                pularlinha = input.nextLine();
                input_anterior[inputs] = menus;
                inputs++;
                i5++;
            }
            if (menus == 7)
            {
                System.out.println("Data: " + dia + "/" + mes + "/" + ano);
                System.out.println("");
                int j;
                int flag = 0;
                
                if (dia == 29 && diasemana == 6)
                {
                    flag = 1;
                }
                else if (dia == 28 && diasemana == 6)
                {
                    flag = 1;
                }
                else if (dia == 30 && diasemana != 7 && diasemana!=1) flag = 1;

                for (j = 0; j < numfuncionarios; j++)
                {
                    if(dados[j][0] == 1 && diasemana == 6)
                    {
                        System.out.println("Nome: "+nomes[j] + "\nEndereco: "+endereco[j] + "\nTipo: Horista");
                        System.out.print("\nMetodo de pagamento: ");
                        if(dados[j][3] == 1) System.out.println("Cheque pelo correio");
                        else if(dados[j][3] == 2) System.out.println("Cheque em maos");
                        else if(dados[j][3] == 3) System.out.println("Debito em conta");
                        if(dados[j][4] == 1) System.out.println("Numero de identificacao no sindicato: "+dados[j][10] + " | Taxa total = "+(dados[j][5] + dados[j][9]) + "%");
                        
                        double salary;
                        
                        if (dados[j][7] > 0) salary = (dados[j][6] * dados[j][1]) + (dados[j][7] * 1.5 * dados[j][1]);
                        else salary = (dados[j][6] * dados[j][1]);
                        if (dados[j][4] == 1) salary = salary - (salary * (dados[j][5] + dados[j][9])/100);
                        System.out.println("Salario: R$"+salary);
                        System.out.println("\n\n");
                    }
                    else if (dados[j][0] == 2 && flag == 1)
                    {
                        System.out.println("Nome: "+nomes[j] + "\nEndereco: "+endereco[j] + "\nTipo: Assalariado");
                        System.out.print("\nMetodo de pagamento: ");
                        if(dados[j][3] == 1) System.out.println("Cheque pelo correio");
                        else if(dados[j][3] == 2) System.out.println("Cheque em maos");
                        else if(dados[j][3] == 3) System.out.println("Debito em conta");
                        if(dados[j][4] == 1) System.out.println("Numero de identificacao no sindicato: "+dados[j][10] + " | Taxa total = "+(dados[j][5] + dados[j][9]) + "%");
                        
                        double salary = dados[j][1];
                        if (dados[j][4] == 1) salary = salary - (salary * (dados[j][5] + dados[j][9])/100);
                        System.out.println("Salario: R$"+salary);
                        System.out.println("\n\n");

                    }
                    
                    else if (dados[j][0] == 3 && diasemana == 6 && ((sexta2%2 == 0) || (sexta2 == 0)))
                    {
                        System.out.println("Nome: "+nomes[j] + "\nEndereco: "+endereco[j] + "\nTipo: Comissionado");
                        System.out.print("\nMetodo de pagamento: ");
                        if(dados[j][3] == 1) System.out.println("Cheque pelo correio");
                        else if(dados[j][3] == 2) System.out.println("Cheque em maos");
                        else if(dados[j][3] == 3) System.out.println("Debito em conta");
                        if(dados[j][4] == 1) System.out.println("Numero de identificacao no sindicato: "+dados[j][10] + " | Taxa total = "+(dados[j][5] + dados[j][9]) + "%");
                        double salary = dados[j][1] + ((dados[j][8] * dados[j][2])/100);
                        if (dados[j][4] == 1) salary = salary - (salary * (dados[j][5] + dados[j][9])/100);
                        System.out.println("Salario: R$"+salary);
                        System.out.println("");

                    }
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
                input_anterior[inputs] = menus;
                inputs++;
            }
            if (menus == 8)
            {
                int action;
                
                int undo_atual = 0;
                //System.out.printf("acao anterior: %d\n",input_anterior[inputs-1]);
                System.out.println("Pressione 1 para desfazer ou 2 para refazer");
                action = input.nextInt();
                pularlinha = input.nextLine();
                
                if (action == 1)
                {
                    
                    undo++;
                    if (input_anterior[inputs-1] == 0)
                    {
                        System.out.println("Nao ha nada para desfazer!");
                        
                    }
                    else if (input_anterior[inputs-1] == 1)
                    {
                        int idu0 = ids0[i0 - 1];
                        dados[idu0][11] = 1.0;
                        id = idu0;
                        numfuncionarios--;
                        input_undo[undo_atual] = input_anterior[inputs-1];
                        undo_atual++;
                        inputs--;
                        i0--;
                        
                    }
                    else if (input_anterior[inputs-1] == 2)
                    {
                        int idu1 = ids1[i1-1];
                        dados[idu1][11] = 0.0;
                        numfuncionarios++;
                        input_undo[undo_atual] = input_anterior[inputs-1];
                        undo_atual++;
                        inputs--;
                        i1--;

                    }
                    else if (input_anterior[inputs-1] == 3)
                    {
                        int idu2 = ids2[i2-1];
                        dados_backup[idu2][6] = dados[idu2][6];
                        dados_backup[idu2][7] = dados[idu2][7];
                        dados[idu2][6] = 0.0;
                        dados[idu2][7] = 0.0;
                        input_undo[undo_atual] = input_anterior[inputs-1]; 
                        undo_atual++;
                        inputs--;
                        i2--;
                    }
                    else if (input_anterior[inputs-1] == 4)
                    {
                        int idu3 = ids3[i3-1];
                        dados_backup[idu3][8] = dados[idu3][8];
                        dados[idu3][8] = 0.0;
                        input_undo[undo_atual] = input_anterior[inputs-1]; 
                        undo_atual++;
                        inputs--;
                        i3--;
                    }
                    else if (input_anterior[inputs-1] == 5)
                    {
                        int idu4 = ids4[i4-1];
                        dados_backup[idu4][9] = dados[idu4][9];
                        dados[idu4][9] = 0.0;
                        input_undo[undo_atual] = input_anterior[inputs-1];
                        undo_atual++;
                        inputs--;
                        i4--;
                    }
                    else if (input_anterior[inputs-1] == 6)
                    {
                        int idu5 = ids5[i5-1];
                        System.out.printf("\nindice: %d\n",idu5);
                        nomes_antes_undo[idu5] = nomes[idu5];
                        nomes[idu5] = nomes_backup[idu5];
                        
                        endereco_antes_undo[idu5] = endereco[idu5];
                        endereco[idu5] = endereco_backup[idu5];
                        int k;
                        for (k = 0; k <=10; k++)
                        {
                            if ((k >= 0 && k<=5) || k == 8 || k == 10)
                            {
                                dados_antes_undo[idu5][k] = dados[idu5][k];
                                dados[idu5][k] = dados_backup[idu5][k];
                                if (k == 0) System.out.println("dado"+dados[idu5][k]);
                            }
                        }
                        input_undo[undo_atual] = input_anterior[inputs-1];
                        undo_atual++;
                        inputs--;
                        i5--;

                    }
                    else if (input_anterior[inputs-1] == 7)
                    {
                        dia--;
                        diasemana--;
                        if (dia == 0 && mes != 1)
                        {
                            dia = 31;
                            mes--;
                        }
                        if (diasemana == 0) diasemana = 7;
                        if (dia == 0 && mes == 1)
                        {
                            ano--;
                            mes = 12;
                            dia = 31;
                        }
                        input_undo[undo_atual] = input_anterior[inputs-1];
                        undo_atual++;
                        inputs--;
                        
                    }


                }
                if (action == 2)
                {
                    if (undo <= 0) System.out.println("Nao ha nada a refazer!");
                    else
                    {
                        redo++;
                        if (input_undo[undo_atual] == 1)
                        {
                            int idu0 = ids0[i0];
                            dados[idu0][11] = 0.0;
                            id = idu0;
                            numfuncionarios++;
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i0++;
                        }
                        else if (input_undo[undo_atual] == 2)
                        {
                            int idu1 = ids1[i1];
                            dados[idu1][11] = 0.0;
                            numfuncionarios--;
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i1++;
                        }
                        else if (input_undo[undo_atual] == 3)
                        {
                            int idu2 = ids2[i2];
                            dados[idu2][6] =  dados_backup[idu2][6];
                            dados[idu2][7] = dados_backup[idu2][7];
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i2++;
                            
                        }
                        else if (input_undo[undo_atual] == 4)
                        {
                            int idu3 = ids3[i3];
                            dados[idu3][8] = dados_backup[idu3][8];
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i3++;
                            
                        }
                        else if (input_undo[undo_atual] == 5)
                        {
                            int idu4 = ids4[i4];
                            dados[idu4][9] = dados_backup[idu4][9];
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i4++;
                            
                        }
                        else if (input_undo[undo_atual] == 6)
                        {
                            int idu5 = ids5[i5];
                            
                            nomes[idu5] = nomes_antes_undo[idu5];
                            
                            endereco[idu5] = endereco_antes_undo[idu5];
                            
                            int k;
                            for (k = 0; k <=10; k++)
                            {
                                if ((k >= 0 && k<=5) || k == 8 || k == 10)
                                {
                                    dados[idu5][k] = dados_antes_undo[idu5][k];
                                
                                }
                            }
                            input_anterior[inputs] = input_undo[undo_atual];
                            undo_atual--;
                            inputs++;
                            i5++;
    
                        }
                        else if (input_undo[undo_atual] == 7)
                        {
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
                        }

                    }
                }

            }
            if (menus == 9)
            {
                System.out.println("Agenda de pagamento:\n");
                System.out.println("Empregados pagos semanalmente:");
                for (i = 0; i < numfuncionarios; i++)
                {
                    if(dados[i][0] == 1 || dados[i][0] == 4 && dados[i][12] == 2) System.out.println("- "+nomes[i]);
                   

                }
                System.out.println("");
                System.out.println("Empregados pagos a cada 2 semanas:");
                for (i = 0; i< numfuncionarios; i++)
                {
                    if(dados[i][0] == 3 || dados[i][0] == 4 && dados[i][12] == 3) System.out.println("- "+nomes[i]);
                }
                System.out.println("");
                System.out.println("Empregados pagos mensalmente:");
                for (i = 0; i<numfuncionarios; i++)
                {
                    if(dados[i][0] == 2 || dados[i][0] == 4 && dados[i][12] == 1) System.out.println("- "+nomes[i]);
                }
                System.out.println("");
            }
            if (menus == 10)
            {
                int id6;
                String pular;
                System.out.println("Digite o numero de identificacao do funcionario");
                id6 = input.nextInt();
                pular = input.nextLine();
                System.out.println("Digite a frequência de pagamento: Mensal ou Semanal.\nSe for mensal, digite o dia do pagamento.\nSe for semanal, informe o intervalo de semanas de pagamento e o dia da semana para ser pago.\nO último dia útil do mês é considerado como $.");
                System.out.println("Por exemplo, se for mensal, digite: mensal 1 (significa que sera pago todo dia 1 do mes).\nSe for semanal, digite: semanal 2 sexta(significa que sera pago a cada 2 semanas na sexta)");
                String newagenda = input.nextLine();

                if(newagenda.charAt(2) == 'n')
                {
                    dados[id6][12] = 1.0;
                    dados[id6][0] = 4.0;
                }
                else if(newagenda.charAt(2) == 'm')
                {
                    if (newagenda.charAt(8) == '1') dados[id6][12] = 2.0;
                    else dados[id6][12] = 3.0;
                    dados[id6][0] = 4.0;
                }

            }
            

        }

        input.close();
        input2.close();
        menu.close();
        
       
        
    }
}