package Folhdadepagamento;
import java.util.*;
public class Horista extends Empregado
{
    private double horasTrabalhadas;
    private double horasExtras;

    public Horista()
    {

        this.setTipo("horista");
        this.horasTrabalhadas = 0;
        this.horasExtras = 0;
        this.setFreq(1);
        this.setDia("sexta");
        this.setNovaAgenda("semanal");

    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {

        this.horasTrabalhadas += horasTrabalhadas;
    }

    public double getHorasExtras() {

        return horasExtras;
    }

    public void setHorasExtras(double horasExtras) {

        this.horasExtras += horasExtras;
    }



    @Override
    public void addEmpregado(Empregado employee, int id) {
        super.addEmpregado(employee, id);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do horista");
        this.setSalario(input.nextDouble());
        System.out.println("Empregado adicionado com sucesso");
    }

    @Override
    public void editarEmpregado(Empregado employee) {
        super.editarEmpregado(employee);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do horista");
        this.setSalario(input.nextDouble());
        System.out.println("Empregado editado com sucesso");
    }

    public void cartaoPonto(Empregado employee)
    {
        double in_h,in_min, out_h, out_min, horas_trabalhadas;
        System.out.println("Digite a hora e minutos de entrada do empregado\nPrimeiro insira a hora e pressione ENTER, depois faça o mesmo com os minutos");
        Scanner input = new Scanner(System.in);
        in_h = input.nextDouble();
        in_min = input.nextDouble();
        System.out.println("Digite a hora e minutos de saida do empregado\nPrimeiro insira a hora e pressione ENTER, depois faça o mesmo com os minutos");
        out_h = input.nextDouble();
        out_min = input.nextDouble();
        if (out_h > in_h)
        {
            horas_trabalhadas = (out_h - in_h) + ((out_min/60) - (in_min/60));
            if(horas_trabalhadas <= 8)
            {
                this.setHorasExtras(horas_trabalhadas);
            }
            else
            {

                this.setHorasTrabalhadas(8);
                this.setHorasExtras(horas_trabalhadas - 8);
            }
            System.out.println("     -----Cartao de Ponto-----");
            System.out.println("Empregado: "+this.getNome() + "\nEntrada: "+(int)in_h + ":" +(int)in_min + "\nSaida: "+(int)out_h + ":"+(int)out_min + "\nHoras trabalhadas: "+(int)horas_trabalhadas);

        }
        else System.out.println("Entradas inválidas");




    }

    @Override
    public void rodarFolha(Empregado employee) {
        super.rodarFolha(employee);
        double salary;

        if (this.getHorasExtras() > 0) salary = (this.getHorasTrabalhadas() * this.getSalario()) + (this.getHorasExtras() * 1.5 * this.getSalario());
        else salary = (this.getHorasTrabalhadas() * this.getSalario());
        if (this.isSindicato()) salary = salary - (salary * (this.getTaxaSindical() + this.getTaxaDeServico())/100);
        System.out.println("Salario: R$"+salary);
        System.out.println("\n\n");
    }


}
