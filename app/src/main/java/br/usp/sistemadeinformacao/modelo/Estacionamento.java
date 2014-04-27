package br.usp.sistemadeinformacao.modelo;

import org.joda.time.DateTime;

/**
 * Created by Willian on 26/04/2014.
 */
public class Estacionamento {
    private Long id;
    private String nome;
    private String endereco;
    private DateTime hrAbertura;
    private DateTime hrFechamento;
    private int numTotalVagas;
    private int numVagasDisponiveis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DateTime getHrAbertura() {
        return hrAbertura;
    }

    public void setHrAbertura(DateTime hrAbertura) {
        this.hrAbertura = hrAbertura;
    }

    public DateTime getHrFechamento() {
        return hrFechamento;
    }

    public void setHrFechamento(DateTime hrFechamento) {
        this.hrFechamento = hrFechamento;
    }

    public int getNumTotalVagas() {
        return numTotalVagas;
    }

    public void setNumTotalVagas(int numTotalVagas) {
        this.numTotalVagas = numTotalVagas;
    }

    public int getNumVagasDisponiveis() {
        return numVagasDisponiveis;
    }

    public void setNumVagasDisponiveis(int numVagasDisponiveis) {
        this.numVagasDisponiveis = numVagasDisponiveis;
    }


}
