package assignment1;

public class Token {
	String token1;
	String token2;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token1 == null) ? 0 : token1.hashCode());
		result = prime * result + ((token2 == null) ? 0 : token2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (token1 == null) {
			if (other.token1 != null)
				return false;
		} else if (!token1.equals(other.token1))
			return false;
		if (token2 == null) {
			if (other.token2 != null)
				return false;
		} else if (!token2.equals(other.token2))
			return false;
		return true;
	}

}
