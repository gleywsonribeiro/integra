/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

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

    private BarChartModel graficoProducao;
    private BarChartModel graficoProducaoTemporal;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    public BarChartModel getGraficoProducao() {
        return graficoProducao;
    }

    public BarChartModel getGraficoProducaoTemporal() {
        return graficoProducaoTemporal;
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

        graficoProducao = initBarModelProducao();
        graficoProducaoTemporal = initBarModelProducaoTemporal();

        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat df2 = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));

        graficoProducao.setTitle("Produção por Funcionário - " + df2.format(date));
        graficoProducaoTemporal.setTitle("Produção por Funcionário (Tempo) - " + df2.format(date));

        Axis xAxis = graficoProducao.getAxis(AxisType.X);
        xAxis.setLabel("Colaboradores");

        Axis yAxis = graficoProducao.getAxis(AxisType.Y);
        yAxis.setLabel("Qtd de Serviços Concluídos");

        graficoProducao.setLegendPosition("ne");
        
        //Configuracao do grafico de tempo
        
        Axis xAxisT = graficoProducaoTemporal.getAxis(AxisType.X);
        xAxisT.setLabel("Colaboradores");

        Axis yAxisT = graficoProducaoTemporal.getAxis(AxisType.Y);
        yAxisT.setLabel("Qtd de Horas");

        graficoProducaoTemporal.setLegendPosition("ne");

    }

    private BarChartModel initBarModelProducaoTemporal() {
        BarChartModel model = new BarChartModel();

        List<Producao> producoes = pdao.getTempoPorTecnico();

        ChartSeries tempoPorTecnico = new BarChartSeries();

        tempoPorTecnico.setLabel("Tempo por Técnico (horas)");

        for (Producao p : producoes) {
            tempoPorTecnico.set(p.getPrimeiroNomeFuncionario(), p.getTempo());
        }

        model.addSeries(tempoPorTecnico);
        model.setSeriesColors("0059b3");
        
        return model;
    }

}
