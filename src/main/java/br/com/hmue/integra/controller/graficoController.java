/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.FuncionarioOS;
import br.com.hmue.integra.modelo.Producao;
import br.com.hmue.integra.modelo.repositorio.DAO;
import br.com.hmue.integra.modelo.repositorio.ProducaoDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Gleywson
 */
@Named(value = "graficoController")
@RequestScoped
public class graficoController {

    @Inject
    private DAO dao;
    @Inject
    private ProducaoDAO pdao;
    private BarChartModel grafico;
    private PieChartModel grafico2;
    private BarChartModel graficoProducao;

    @PostConstruct
    public void init() {
        createBarModel();
        createPieModel();
    }

    public BarChartModel getGrafico() {
        return grafico;
    }

    public PieChartModel getGrafico2() {
        return grafico2;
    }

    public BarChartModel getGraficoProducao() {
        return graficoProducao;
    }
    
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        List<FuncionarioOS> funcionarios = dao.OSPorFuncionario();

         ChartSeries serie = new BarChartSeries();
         
        for (FuncionarioOS funcionario : funcionarios) {
           //serie.setLabel(funcionario.getPrimeiroNome());
            serie.set(funcionario.getPrimeiroNome(), funcionario.getQuantidade());
        }
        model.addSeries(serie);
        model.setSeriesColors("006600");

        return model;
    }
    
    private BarChartModel initBarModelProducao() {
        BarChartModel model = new BarChartModel();
        
        List<Producao> concluidas = pdao.getServicosConcluidos();

       

         ChartSeries avaliadas = new BarChartSeries();
         ChartSeries naoAvaliadas = new BarChartSeries();
         
         avaliadas.setLabel("Avaliadas");
         naoAvaliadas.setLabel("Não Avaliadas");
         
         for (Producao p : concluidas) {
            avaliadas.set(p.getPrimeiroNomeFuncionario(), p.getAvaliadas());
            naoAvaliadas.set(p.getPrimeiroNomeFuncionario(), p.getNaoAvaliadas());
        }
         
        model.addSeries(avaliadas);
        model.addSeries(naoAvaliadas);
        model.setSeriesColors("006600, 990000");
        model.setStacked(true);
        return model;
    }

    private void createBarModel() {
        grafico = initBarModel();
        graficoProducao = initBarModelProducao();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        DateFormat df2 = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));

        grafico.setTitle("Serviços Concluídos por Funcionario - " + df2.format(data));
        graficoProducao.setTitle("Produção por Funcionário - " + df2.format(data));
          
        Axis xAxis = graficoProducao.getAxis(AxisType.X);
        xAxis.setLabel("Colaboradores");

        Axis yAxis = graficoProducao.getAxis(AxisType.Y);
        yAxis.setLabel("Qtd de Serviços Concluídos");
        
        grafico.setAnimate(true);
        graficoProducao.setLegendPosition("ne");
  
    }
    
    private void createPieModel() {
        grafico2 = new PieChartModel();
        List<FuncionarioOS> funcionarios = dao.OSPorFuncionario();
        
        for (FuncionarioOS funcionario : funcionarios) {
             grafico2.set(funcionario.getNome(), funcionario.getQuantidade());
        }
       
        grafico2.setTitle("Serviços por Funcionário");
        grafico2.setLegendPosition("w");

    }

}
