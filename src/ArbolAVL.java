import java.util.HashMap;
import java.util.Map;

class ArbolAVL {
    NodoAVL raiz;
    Map<Integer, Integer> copiasPorISBN = new HashMap<>();

    private int altura(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    private int obtenerFactorBalance(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    public NodoAVL insertar(NodoAVL nodo, int isbn, String titulo, String volumen, String editorial, String biblioteca, String autor, String biografiaAutor) {
        if (nodo == null)
            return new NodoAVL(isbn, titulo, volumen, editorial, biblioteca, autor, biografiaAutor);

        if (isbn < nodo.isbn)
            nodo.izquierda = insertar(nodo.izquierda, isbn, titulo, volumen, editorial, biblioteca, autor, biografiaAutor);
        else if (isbn > nodo.isbn)
            nodo.derecha = insertar(nodo.derecha, isbn, titulo, volumen, editorial, biblioteca, autor, biografiaAutor);
        else
            return nodo;

        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));

        int factorBalance = obtenerFactorBalance(nodo);

        if (factorBalance > 1 && isbn < nodo.izquierda.isbn)
            return rotacionDerecha(nodo);

        if (factorBalance < -1 && isbn > nodo.derecha.isbn)
            return rotacionIzquierda(nodo);

        if (factorBalance > 1 && isbn > nodo.izquierda.isbn) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }

        if (factorBalance < -1 && isbn < nodo.derecha.isbn) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int isbn, String titulo, String volumen, String editorial, String biblioteca, String autor, String biografiaAutor) {
        raiz = insertar(raiz, isbn, titulo, volumen, editorial, biblioteca, autor, biografiaAutor);
        copiasPorISBN.put(isbn, copiasPorISBN.getOrDefault(isbn, 0) + 1);
    }

    public void imprimirEnOrden(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrden(nodo.izquierda);
            System.out.println("Título: " + nodo.titulo);
            System.out.println("ISBN: " + nodo.isbn);
            System.out.println("Volumen: " + nodo.volumen);
            System.out.println("Editorial: " + nodo.editorial);
            System.out.println("Biblioteca: " + nodo.biblioteca);
            System.out.println("Autor: " + nodo.autor);
            System.out.println("Biografía del Autor: " + nodo.biografiaAutor);
            System.out.println();
            imprimirEnOrden(nodo.derecha);
        }
    }

    public NodoAVL eliminar(NodoAVL raiz, int isbn) {
        if (raiz == null)
            return raiz;

        if (isbn < raiz.isbn)
            raiz.izquierda = eliminar(raiz.izquierda, isbn);
        else if (isbn > raiz.isbn)
            raiz.derecha = eliminar(raiz.derecha, isbn);
        else {
            if ((raiz.izquierda == null) || (raiz.derecha == null)) {
                NodoAVL temp = null;
                if (temp == raiz.izquierda)
                    temp = raiz.derecha;
                else
                    temp = raiz.izquierda;

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;

            } else {
                NodoAVL temp = obtenerNodoMinimo(raiz.derecha);
                raiz.isbn = temp.isbn;
                raiz.derecha = eliminar(raiz.derecha, temp.isbn);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = max(altura(raiz.izquierda), altura(raiz.derecha)) + 1;
        int factorBalance = obtenerFactorBalance(raiz);

        if (factorBalance > 1 && obtenerFactorBalance(raiz.izquierda) >= 0)
            return rotacionDerecha(raiz);

        if (factorBalance > 1 && obtenerFactorBalance(raiz.izquierda) < 0) {
            raiz.izquierda = rotacionIzquierda(raiz.izquierda);
            return rotacionDerecha(raiz);
        }

        if (factorBalance < -1 && obtenerFactorBalance(raiz.derecha) <= 0)
            return rotacionIzquierda(raiz);

        if (factorBalance < -1 && obtenerFactorBalance(raiz.derecha) > 0) {
            raiz.derecha = rotacionDerecha(raiz.derecha);
            return rotacionIzquierda(raiz);
        }

        return raiz;
    }

    private NodoAVL obtenerNodoMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izquierda != null)
            actual = actual.izquierda;
        return actual;
    }

    public int obtenerCopiasPorISBN(int isbn) {
        return copiasPorISBN.getOrDefault(isbn, 0);
    }

    public NodoAVL buscarPorISBN(NodoAVL nodo, int isbn) {
        if (nodo == null) {
            return null;
        }
        if (isbn < nodo.isbn) {
            return buscarPorISBN(nodo.izquierda, isbn);
        } else if (isbn > nodo.isbn) {
            return buscarPorISBN(nodo.derecha, isbn);
        } else {
            return nodo;
        }
    }
}