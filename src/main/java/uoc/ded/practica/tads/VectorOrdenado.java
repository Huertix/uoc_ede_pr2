package uoc.ded.practica.tads;

import uoc.ei.tads.ContenedorAcotado;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;

// TODO: indicar que no es necesario implementar diccionario
public abstract class VectorOrdenado<E> implements ContenedorAcotado<E>{

    protected E[] elementos;
    protected int maxElementos;
    protected int nElementos;
    protected int primero;


    @Override
    public boolean estaLleno() {
        return this.nElementos == this.elementos.length;
    }

    @Override
    public boolean estaVacio() {
        return this.nElementos == 0;
    }

    @Override
    public int numElems() {
        return this.nElementos;
    }

    @Override
    public Iterador<E> elementos() {
        return new IteradorVectorImpl<E>(elementos, this.numElems(), primero);
    }

}
