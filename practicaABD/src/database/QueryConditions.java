package database;


public class QueryConditions {
	private String clave;
	private Operator operator;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return clave+operator.toString()+'?';
	}
	private Object valor;
	public QueryConditions(String clave, Operator operator, Object valor) {
		super();
		this.clave = clave;
		this.operator = operator;
		this.valor = valor;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
}
