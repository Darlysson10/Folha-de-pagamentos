package Folhdadepagamento;
import java.util.*;
public abstract class Empregado {
    private String nome;
    private String endereco;
    private String tipo;
    private double salario;
    private String metodoDePagamento;
    private boolean sindicato;
    private double taxaSindical;
    private double taxaDeServico;
    private int id;
    private int idSindicato;
    private String novaAgenda;
    private int freq;
    private String dia;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNovaAgenda() {
        return novaAgenda;
    }

    public void setNovaAgenda(String novaAgenda) {
        this.novaAgenda = novaAgenda;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Empregado() {
        this.sindicato = false;
        this.taxaSindical = 0.0;
        this.taxaDeServico = 0.0;
        this.novaAgenda ="Sem nova agenda";

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(String metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public boolean isSindicato() {
        return sindicato;
    }

    public void setSindicato(boolean sindicato) {
        this.sindicato = sindicato;
    }

    public double getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public double getTaxaDeServico() {
        return taxaDeServico;
    }

    public void setTaxaDeServico(double taxaDeServico) {
        this.taxaDeServico = taxaDeServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(int idSindicato) {
        this.idSindicato = idSindicato;
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipo='" + tipo + '\'' +
                ", salario=" + salario +
                ", metodoDePagamento='" + metodoDePagamento + '\'' +
                ", sindicato=" + sindicato +
                ", taxaSindical=" + taxaSindical +
                ", taxaDeServico=" + taxaDeServico +
                ", id=" + id +
                ", idSindicato=" + idSindicato +
                ", novaAgenda=" + novaAgenda +
                '}';
    }

    public void addEmpregado(Empregado employee, int id) {
        Scanner input = new Scanner(System.in);
        String linha;
        double in1;
        int in2;
        System.out.println("Digite o nome do empregado:");
        this.setNome(input.nextLine());
        System.out.println("Digite o endereço do empregado:");
        this.setEndereco(input.nextLine());
        System.out.println("Digite o método de pagamento:\n'correio' - Cheque pelo correio\n'maos' - Cheque em mãos\n'deposito' - Depósito na conta bancária");
        this.setMetodoDePagamento(input.nextLine());
        System.out.println("Pertence a um sindicato? 1 - Sim, 0 - Não");
        in2 = input.nextInt();
        if (in2 == 1) {
            setSindicato(true);
            this.setIdSindicato(id);
            System.out.println("Digite a taxa sindical");
            this.setTaxaSindical(input.nextDouble());
        }
        employee.setId(id);


    }

    public void cartaoPonto(Empregado employee) {

    }

    public void vendas(Empregado employee) {

    }

    public void editarEmpregado(Empregado employee) {
        int in2;
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome do empregado: ");
        this.setNome(input.nextLine());
        System.out.print("Digite o endereco do empregado: ");
        this.setEndereco(input.nextLine());
        System.out.println("Digite o método de pagamento:\n'correio' - Cheque pelo correio\n'maos' - Cheque em mãos\n'deposito' - Depósito na conta bancária");
        this.setMetodoDePagamento(input.nextLine());
        System.out.println("Pertence a um sindicato? 1 - Sim, 0 - Não");
        in2 = input.nextInt();
        if (in2 == 1) {
            setSindicato(true);
            this.setIdSindicato(this.getId());
            System.out.println("Digite a taxa sindical");
            this.setTaxaSindical(input.nextDouble());
        } else setSindicato(false);

    }

    public void rodarFolha(Empregado employee) {
        System.out.println("Nome: " + this.getNome() + "\nEndereco: " + this.getEndereco() + "\nTipo: " + this.getTipo());
        System.out.print("\nMetodo de pagamento: ");
        if (this.getMetodoDePagamento().equals("correio")) System.out.println("Cheque pelo correio");
        else if (this.getMetodoDePagamento().equals("maos")) System.out.println("Cheque em maos");
        else if (this.getMetodoDePagamento().equals("debito")) System.out.println("Debito em conta");

        if (this.isSindicato())
            System.out.println("Numero de identificacao no sindicato: " + this.getIdSindicato() + " | Taxa total = " + (this.getTaxaDeServico() + this.getTaxaSindical() + "%"));
    }

    public void novaAgenda(Empregado employee)
    {
        if (this.getNovaAgenda().equals("semanal") &&  this.getFreq() == 1)
        {
            System.out.println("- "+this.getNome() + "é pago(a) a cada 1 semana na " +this.getDia());
        }
        else if (this.getNovaAgenda().equals("semanal") &&  this.getFreq() == 2)
        {
            System.out.println("- "+this.getNome() + "é pago(a) a cada 2 semanas na " +this.getDia());
        }
        else if (this.getNovaAgenda().equals("mensal"))
        {
            System.out.println("- "+this.getNome() + "é pago(a) mensalmente");
        }
        else System.out.println("Erro. Verifique se digitou a palavra como exatamente como foi designada");


    }


}

