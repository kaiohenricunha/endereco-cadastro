package com.tbp.model;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome")
    String nome;
    @Column(name = "senha")
    String senha;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "username", unique = true)
    String username;
    @Column(name = "cep")
    private String cep;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Endereco endereco;

    public Usuario(String nome, String senha, String email, String username, String cep, Endereco endereco) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.username = username;
        this.cep = cep;
    }

    public Usuario(String nome, String senha, String email, String username, String cep) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.username = username;
        this.cep = cep;
    }

    public Usuario() {

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", cep='" + cep + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
