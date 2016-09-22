package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Joel Zatti joelzatti@gmail.com
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_nota", sequenceName = "seq_nota_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_nota", strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull(message = "A primeira nota deve ser informada")
    @Column(name = "nota01", columnDefinition = "numeric(2,2)")
    private double nota01;

    @NotNull(message = "A segunda nota deve ser informada")
    @Column(name = "nota02", columnDefinition = "numeric(2,2)")
    private double nota02;

    @Column(name = "media", columnDefinition = "numeric(2,2)")
    private double media;

    @NotNull(message = "O aluno deve ser informado")
    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
    private Aluno aluno;

    @NotNull(message = "A disciplina não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id", nullable = false)
    private Disciplina disciplina;

    public Nota() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota01() {
        return nota01;
    }

    public void setNota01(double nota01) {
        this.nota01 = nota01;
    }

    public double getNota02() {
        return nota02;
    }

    public void setNota02(double nota02) {
        this.nota02 = nota02;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final Nota other = (Nota) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
