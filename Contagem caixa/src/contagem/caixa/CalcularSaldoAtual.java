package contagem.caixa;
public class CalcularSaldoAtual extends TelaCaixa{
    double saldo = 0.0;
    
    public double calcularSaldoAtual(){
     

    // Obtém as quantidades de notas e moedas do seu formulário e multiplica pelos valores correspondentes
    saldo += Double.parseDouble(n100.getText()) * 100;
    saldo += Double.parseDouble(n50.getText()) * 50;
    saldo += Double.parseDouble(n20.getText()) * 20;
    saldo += Double.parseDouble(n10.getText()) * 10;
    saldo += Double.parseDouble(n5.getText()) * 5;
    saldo += Double.parseDouble(n2.getText()) * 2;
    saldo += Double.parseDouble(n1.getText());
    saldo += Double.parseDouble(n050.getText()) * 0.50;
    saldo += Double.parseDouble(n025.getText()) * 0.25;
    saldo += Double.parseDouble(n010.getText()) * 0.10;
    //saldo += Double.parseDouble(getN005().getText()) * 0.05;
    saldo += Double.parseDouble(nMeio.getText());

    return saldo;
}
    
}
