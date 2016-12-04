package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Joel Zatti joelzatti@gmail.com
 */
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Length(max = 10, message = "A sigla não pode ter mais de {max} caracteres")
    @NotNull(message = "A sigla não pode ser nula")
    @NotBlank(message = "A sigla não pode estar em branco")
    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode estar em branco")
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @NotNull(message = "O ativo não pode ser nulo")
    @Column(nullable = false)
    private Boolean ativo;

    @NotNull(message = "O início das atividades deve ser informado")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inicioAtividades", nullable = false)
    private Calendar inicioAtividades;

    @NotNull(message = "A instituição não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "instituicao", referencedColumnName = "id", nullable = false)
    private Instituicao instituicao;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Disciplina> disciplina = new ArrayList<>();

    public void adicionarDisciplina(Disciplina obj) {
        obj.setCurso(this);
        this.disciplina.add(obj);
    }

    public void removerDisciplina(int index) {
        this.disciplina.remove(index);
    }

    public Curso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getInicioAtividades() {
        return inicioAtividades;
    }

    public void setInicioAtividades(Calendar inicioAtividades) {
        this.inicioAtividades = inicioAtividades;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Curso other = (Curso) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public List<Disciplina> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(List<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }

}
