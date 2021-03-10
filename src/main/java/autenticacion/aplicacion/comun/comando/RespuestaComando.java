package autenticacion.aplicacion.comun.comando;

public class RespuestaComando<T> {

	private T valor;

	public RespuestaComando(T valor) {
		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}
}
