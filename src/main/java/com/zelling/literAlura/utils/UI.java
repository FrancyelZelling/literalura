package com.zelling.literAlura.utils;

public class UI {

    public void showMenu(){
        System.out.println(
                """
               
               ******************************
               >>> Menu
               ******************************
               
               1 - Buscar livro pelo titulo
               2 - Listar livros buscados
               3 - Listar autores registrados
               4 - Listar autores vivos em um determinado ano
               5 - Listar livros em um determinado idioma
               
               ******************************
               
               """
        );
    }
}
