package com.example.oblig1;
// Ticket class
public class Ticket {
    // Ticket counter for id
    private int counter = 0;

    private Integer id;
    private String film;
    private int antall;
    private String fornavn;
    private String etternavn;
    private String telefon;
    private String epost;

    // Default constructor, needed for reading json after serialization
    public Ticket() {
        this.id = ++counter; // Auto generate ID
    }
    public Ticket(String film, int antall, String fornavn, String etternavn, String telefon, String epost) {
        // Auto generate ID
        this.id = ++counter; // Auto generate ID
        this.film = film;
        this.antall = antall;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefon = telefon;
        this.epost = epost;
    }

    // getters and setters
    public Integer getId() {
        return this.id;
    }

    // No setter for ID, as it should be auto generated
    public String getFilm() {
        return this.film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    // Repeat for the other fields
    public int getAntall() {
        return this.antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public String getFornavn() {
        return this.fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return this.etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEpost() {
        return this.epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

}