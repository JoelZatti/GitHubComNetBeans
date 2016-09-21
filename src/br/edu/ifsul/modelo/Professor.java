
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Joel Zatti
 * joelzatti@gmail.com
 */
@Entity
@Table(name = "professor")
public class Professor implements Serializable{
    @NotNull(message = "A titulação não pode ser nula")
    @NotBlank(message = "A titulação deve ser informado")
    @Length(max = 20, message = "A titulação não pode ter mais de {max} caracteres")
    @Column(name = "titulacao", length = 20, nullable = false, unique = true)
    private String titulacao;
    @NotNull(message = "Os Tópicos de Interesse não podem ser nulos")
    @NotBlank(message = "Os Tópicos de Interesse deve ser informado")
    @Column(name = "topicosInteresse", columnDefinition = "text")
    private String topicosInteresse;

    public Professor() {
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getTopicosInteresse() {
        return topicosInteresse;
    }

    public void setTopicosInteresse(String topicosInteresse) {
        this.topicosInteresse = topicosInteresse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.titulacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.titulacao, other.titulacao)) {
            return false;
        }
        return true;
    }
    

}
