
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "disciplina")
public class Disciplina implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_disciplina", sequenceName = "seq_disciplina_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_disciplina", strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode estar em branco")
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;
    
    @NotNull(message = "A carga horária não pode ser nula")
    @Column(name = "cargaHoraria", columnDefinition = "numeric(2,2)")  
    private double cargaHoraria;
    
    @NotBlank(message = "O campo conhecimentos  mínimos não pode ficar em branco")
    @Column(name = "conhecimentosMinimos", columnDefinition = "text")
    private String conhecimentosMinimos;
    
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Nota> notas = new ArrayList<>();  
    
    @NotNull(message = "O curso não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso; 
    
    public void adicionarNota(Nota obj){
        obj.setDisciplina(this);
        this.notas.add(obj);        
    }
    
    public void removerNota(int index){
        this.notas.remove(index);
    }
    
    public Disciplina() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getConhecimentosMinimos() {
        return conhecimentosMinimos;
    }

    public void setConhecimentosMinimos(String conhecimentosMinimos) {
        this.conhecimentosMinimos = conhecimentosMinimos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
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
        final Disciplina other = (Disciplina) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
