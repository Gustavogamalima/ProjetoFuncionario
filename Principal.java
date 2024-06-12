import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(func -> func.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações.
        System.out.println("3.3 – Todos os Funcionários:");
        for (Funcionario func : funcionarios) {
            System.out.println(func.toString());
        }

        // 3.4 – Aumentar salário em 10%
        System.out.println("\n3.4 – Funcionários com 10% de aumento:");
        for (Funcionario func : funcionarios) {
            BigDecimal novoSalario = func.getSalario().multiply(BigDecimal.valueOf(1.1));
            func.setSalario(novoSalario);
            System.out.println(func);
        }

        // 3.5 – Agrupar os funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("\n3.6 – Funcionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario func : funcionariosPorFuncao.get(funcao)) {
                System.out.println(func);
            }
        }

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\n3.8 – Funcionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario func : funcionarios) {
            Month mesAniversario = func.getDataNascimento().getMonth();
            if (mesAniversario == Month.OCTOBER || mesAniversario == Month.DECEMBER) {
                System.out.println(func);
            }
        }

        // 3.9 – Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(func -> Period.between(func.getDataNascimento(), LocalDate.now()).getYears()));
        int idadeMaisVelho = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\n3.9 – Funcionário mais velho: Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + idadeMaisVelho);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\n3.10 – Funcionários ordenados por nome:");
        for (Funcionario func : funcionariosOrdenados) {
            System.out.println(func);
        }

        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n3.11 – Total dos salários: " + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".", ","));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n3.12 – Salários em termos de salário mínimo:");
        for (Funcionario func : funcionarios) {
            BigDecimal qtdSalariosMinimos = func.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(func.getNome() + " ganha " + qtdSalariosMinimos.toString().replace(".", ",") + " salários mínimos.");
        }
    }
}
