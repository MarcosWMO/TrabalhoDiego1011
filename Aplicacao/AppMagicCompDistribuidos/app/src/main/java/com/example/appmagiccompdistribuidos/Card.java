package com.example.appmagiccompdistribuidos;

import android.widget.EditText;
import android.widget.TextView;

public class Card {

    private String nomeCarta;
    private String descricaoCarta;
    private int forcaCarta;
    private int agilidadeCarta;
    private int resistenciaCarta;

    public Card (){}

    public Card(String nomeCarta, String descricaoCarta, int forcaCarta, int agilidadeCarta, int resistenciaCarta) {
        this.nomeCarta = nomeCarta;
        this.descricaoCarta = descricaoCarta;
        this.forcaCarta = forcaCarta;
        this.agilidadeCarta = agilidadeCarta;
        this.resistenciaCarta = resistenciaCarta;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public void setNomeCarta(String nomeCarta) {
        this.nomeCarta = nomeCarta;
    }

    public String getDescricaoCarta() {
        return descricaoCarta;
    }

    public void setDescricaoCarta(String descricaoCarta) {
        this.descricaoCarta = descricaoCarta;
    }

    public int getForcaCarta() {
        return forcaCarta;
    }

    public void setForcaCarta(int forcaCarta) {
        this.forcaCarta = forcaCarta;
    }

    public int getAgilidadeCarta() {
        return agilidadeCarta;
    }

    public void setAgilidadeCarta(int agilidadeCarta) {
        this.agilidadeCarta = agilidadeCarta;
    }

    public int getResistenciaCarta() {
        return resistenciaCarta;
    }

    public void setResistenciaCarta(int resistenciaCarta) {
        this.resistenciaCarta = resistenciaCarta;
    }

    public String toString() {
        return  "\nCarta = " + nomeCarta + "\n" +
                "Descrição = " + descricaoCarta + "\n" +
                "Força = " + forcaCarta + "\n" +
                "Agilidade = " + agilidadeCarta + "\n" +
                "Resistência = " + resistenciaCarta + "\n";
    }

}
