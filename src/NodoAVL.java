class NodoAVL {
    int isbn;
    String titulo;
    String volumen;
    String editorial;
    String biblioteca;
    String autor; // Aquí almacenamos nombre y apellido del autor
    String biografiaAutor;
    NodoAVL izquierda;
    NodoAVL derecha;
    int altura;


    public NodoAVL(int isbn, String titulo, String volumen, String editorial, String biblioteca, String autor, String biografiaAutor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.volumen = volumen;
        this.editorial = editorial;
        this.biblioteca = biblioteca;
        this.autor = autor;
        this.biografiaAutor = biografiaAutor;
        this.altura = 1;

    }
}
