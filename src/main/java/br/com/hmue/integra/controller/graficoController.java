/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.FuncionarioOS;
import br.com.hmue.integra.modelo.repositorio.DAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private BarChartModel grafico;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    public BarChartModel getGrafico() {
        return grafico;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        List<FuncionarioOS> funcionarios = dao.OSPorFuncionario();

        for (FuncionarioOS funcionario : funcionarios) {
            ChartSeries serie = new BarChartSeries();
            serie.setLabel(funcionario.getPrimeiroNome());
            serie.set("                   ", funcionario.getQuantidade());
            model.addSeries(serie);
        }

        return model;
    }

    private void createBarModel() {
        grafico = initBarModel();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        DateFormat df2 = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));

        grafico.setTitle("Serviços Concluídos por Funcionario - " + df2.format(data));
        grafico.setLegendPosition("ne");

        Axis xAxis = grafico.getAxis(AxisType.X);
        xAxis.setLabel("Colaboradores");

        Axis yAxis = grafico.getAxis(AxisType.Y);
        yAxis.setLabel("Qtd de Serviços Concluídos");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
    }

}
