package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import com.ifpb.dac.interfaces.CoordenadorDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author lyndemberg
 */
@Named
@SessionScoped
public class ControladorCRUDCoord implements Serializable {

    @Inject
    private CoordenadorDao coordenadorDao;
    @Inject
    private CursoDao cursoDao;
    private Coordenador coordenador = new Coordenador();
    private String regime;
    private String vinculo;
    private String unidade;
    private String selectCurso;
    private boolean editando = false;
    private Coordenador coordAntesDeEditar = new Coordenador();

    public String excluirCoordenador(Coordenador coord) {
        coordenadorDao.remover(coord);
        return null;
    }

    public String cadastrarCoordenador() {
        Curso curso = cursoDao.retornarPorNome(selectCurso);
        if (coordenadorDao.verificarEmail(coordenador.getEmail())) {
            FacesMessage msg = new FacesMessage("Ja existe um coordenador cadastrado com esse email");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Cadastro", msg);
        } else if (curso.getCoordenador() != null) {
            FacesMessage msg = new FacesMessage("O curso já tem coordenador, exclua-o primeiro");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Cadastro", msg);
        } else if (!areQualifiedDates(coordenador.getInicioMandato(), coordenador.getFimMandato())) {
            FacesMessage msg = new FacesMessage("A data de inicio do mandato é maior que a de fim");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Cadastro", msg);
        } else {
            coordenador.setRegime(Enum.valueOf(Regime.class, regime));
            coordenador.setVinculo(Enum.valueOf(Vinculo.class, vinculo));
            coordenador.setUnidade(Enum.valueOf(Unidade.class, unidade));
            coordenador.setLogado(true);
            coordenador.setCurso(curso);
            curso.setCoordenador(coordenador);
            cursoDao.atualizar(curso);
            coordenador = new Coordenador();
            selectCurso = null;
        }
        return null;
    }

    public boolean areQualifiedDates(LocalDate inicioMandato, LocalDate fimMandato) {
        return inicioMandato.isBefore(fimMandato);
    }

    public String editarCoordenador(Coordenador coord) {
        editando = true;
        coordenador = coord;
        selectCurso = coord.getCurso().getInfo().getDescricao();
        coordAntesDeEditar = coord;

        return null;
    }

    public String atualizarCoordenador() {
        editando = false;

        Curso curso = cursoDao.retornarPorNome(selectCurso);
        Curso cursoAntigo = coordAntesDeEditar.getCurso();
        if (coordenadorDao.verificarEmail(coordenador.getEmail()) && !coordenador.getEmail().equals(coordAntesDeEditar.getEmail())) {

            FacesMessage msg = new FacesMessage("Ja existe um coordenador cadastrado com esse email");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Atualizar", msg);

        } else if (curso.getCoordenador() != null
                && !curso.getCoordenador().getEmail().equals(coordAntesDeEditar.getEmail())) {

            FacesMessage msg = new FacesMessage("O curso já tem coordenador, exclua-o primeiro para proseguir");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Atualizar", msg);

        } else if (!areQualifiedDates(coordAntesDeEditar.getInicioMandato(), coordAntesDeEditar.getFimMandato())) {
            FacesMessage msg = new FacesMessage("A data de inicio do mandato é maior que a de fim");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Cadastro", msg);

        } else {
            //RETIRANDO O COORDENADOR DO CURSO ANTIGO, CASO UM NOVO CURSO TENHA SIDO ESCOLHIDO
            if (cursoAntigo != curso) {
                cursoAntigo.setCoordenador(null);
                cursoDao.atualizar(cursoAntigo);
            }

            coordenador.setRegime(Enum.valueOf(Regime.class, regime));
            coordenador.setVinculo(Enum.valueOf(Vinculo.class, vinculo));
            coordenador.setUnidade(Enum.valueOf(Unidade.class, unidade));
            coordenador.setCurso(curso);
            curso.setCoordenador(coordenador);

            cursoDao.atualizar(curso);

            //LIMPANDO INSTÂNCIAS PARA PRÓXIMOS USOS NA SESSÃO
            coordenador = new Coordenador();
            coordAntesDeEditar = new Coordenador();
            selectCurso = null;
        }
        return null;
    }

    public List<Coordenador> listarTodos() {
        return coordenadorDao.listarTodos();
    }

    public List<String> getCursosNomes() {
        return cursoDao.listarNomeCursos();
    }

    public List<String> getRegimes() {
        return Arrays.asList("DE", "T40");
    }

    public List<String> getVinculos() {
        return Arrays.asList("Efetivo", "Substituto");
    }

    public List<String> getUnidades() {
        return Arrays.asList("UFGP", "UNIND", "UNINFO");
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String getSelectCurso() {
        return selectCurso;
    }

    public void setSelectCurso(String selectCurso) {
        this.selectCurso = selectCurso;
    }

}
