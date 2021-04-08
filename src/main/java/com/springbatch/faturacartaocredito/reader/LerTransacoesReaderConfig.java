package com.springbatch.faturacartaocredito.reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.springbatch.faturacartaocredito.dominio.CartaoCredito;
import com.springbatch.faturacartaocredito.dominio.Cliente;
import com.springbatch.faturacartaocredito.dominio.Transacao;

@Configuration
public class LerTransacoesReaderConfig {
	@Bean
	public JdbcPagingItemReader<Transacao> lerTransacoesReader(
			@Qualifier("appDataSource") DataSource dataSource,
			PagingQueryProvider queryProvider) {
		return new JdbcPagingItemReaderBuilder<Transacao>()
				.name("lerTransacoesReader")
				.dataSource(dataSource)
				.pageSize(1)
				.queryProvider(queryProvider)
				.rowMapper(rowMapperTransacao())
				.saveState(false)
				.build();
	}
	
	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean provider =
                new SqlPagingQueryProviderFactoryBean();
 
        provider.setDataSource(dataSource);
        provider.setSelectClause("select *");
        provider.setFromClause("from transacao join cartao_credito using (numero_cartao_credito)");
        provider.setSortKeys(sortByNumeroCartaoAsc());
 
        return provider;
	}
	
	private Map<String, Order> sortByNumeroCartaoAsc() {
        Map<String, Order> sortConfiguration = new HashMap<>();
        sortConfiguration.put("numero_cartao_credito", Order.ASCENDING);
        return sortConfiguration;
    }

	private RowMapper<Transacao> rowMapperTransacao() {
		return new RowMapper<Transacao>() {

			@Override
			public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartaoCredito cartaoCredito = new CartaoCredito();
				cartaoCredito.setNumeroCartaoCredito(rs.getInt("numero_cartao_credito"));
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente"));
				cartaoCredito.setCliente(cliente);
				
				Transacao transacao = new Transacao();
				transacao.setId(rs.getInt("id"));
				transacao.setCartaoCredito(cartaoCredito);
				transacao.setData(rs.getDate("data"));
				transacao.setValor(rs.getDouble("valor"));
				transacao.setDescricao(rs.getString("descricao"));
				
				return transacao;
			}
			
		};
	}
}
