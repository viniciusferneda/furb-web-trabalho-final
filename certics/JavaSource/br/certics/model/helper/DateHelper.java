package br.certics.model.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Classe helper para manipulação de datas.
 * 
 * @author Thyago Schleuss - thyago@dynamix.com.br
 * @since 13/08/2012
 */
public final class DateHelper {

	/**
	 * Total de milisegundos em 1 dia (24 horas).<br />
	 * Equivalente a: 1000 * 60 * 60 * 24
	 */
	public static final int MILISEGUNDOS_DIA = 86400000;

	/**
	 * Pattern padrão: <b>dd/MM/yyyy HH:mm:ss</b>
	 */
	public static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Patter normalmente utilizado em views: <b>dd/MM/yyyy</b>
	 */
	public static final String VIEW_PATTERN = "dd/MM/yyyy";

	/**
	 * Construtor padrão, não deve ser instanciado.
	 */
	private DateHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Formate uma data para o pattern estabelecido
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(final Date date, final String pattern) {
		if (date != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * Retorna a data atual formatada de acordo com o pattern estabelecido.
	 * 
	 * @param pattern
	 * @return
	 */
	public static String now(final String pattern) {
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	/**
	 * Retorna a data atual formatada de acordo com o pattern padrão definido em
	 * {@link DateHelper#DEFAULT_PATTERN}.
	 * 
	 * @return
	 */
	public static String now() {
		return now(DEFAULT_PATTERN);
	}

	/**
	 * Formata uma data que necessite de timezone, para ser exibida em tela.
	 * 
	 * @param objDate
	 * @param strTimezone
	 * @return
	 */
	public static String formatToView(final Object objDate,
			final Object strTimezone) {

		String formattedDate = "";
		final Date dtAtendimento = (Date) objDate;
		final String dtAtendimentoTz = String.valueOf(strTimezone);

		if (dtAtendimento != null) {

			final SimpleDateFormat sdf = new SimpleDateFormat(VIEW_PATTERN);
			Calendar calendar = null;

			if (strTimezone != null) {
				TimeZone timeZone = TimeZone.getTimeZone(dtAtendimentoTz);
				calendar = new GregorianCalendar(
						TimeZone.getTimeZone(dtAtendimentoTz));
				calendar.setTime(dtAtendimento);
				sdf.setTimeZone(timeZone);
			} else {
				calendar = new GregorianCalendar();
				calendar.setTime(dtAtendimento);
			}

			formattedDate = sdf.format(calendar.getTime());
		}

		return formattedDate;
	}

	/**
	 * Calcula determinado prazo restante em dias.
	 * 
	 * @param dtInicio
	 *            Data de inicio do evento
	 * @param nrDiasIgnorar
	 *            Total de dias para determinado prazo expirar.
	 * @return Dias restante para zerar o prazo.
	 */
	public static int calculaDiasPrazo(final Date dtInicio,
			final Integer nrDiasPrazo) {

		// Data ignorar
		final Calendar calendarIgnorar = calculaDataPrazo(dtInicio,
				nrDiasPrazo, false, false, false, false);

		// Hoje
		final Calendar calendarHoje = Calendar.getInstance();
		calendarHoje.set(Calendar.HOUR_OF_DAY, 0);
		calendarHoje.set(Calendar.MINUTE, 0);
		calendarHoje.set(Calendar.SECOND, 0);
		calendarHoje.set(Calendar.MILLISECOND, 0);

		// Calcula a diferenca
		final long diferencaMilisegundos = calendarIgnorar.getTimeInMillis()
				- calendarHoje.getTimeInMillis();
		double diferencaDias = diferencaMilisegundos / MILISEGUNDOS_DIA;

		return (int) diferencaDias;
	}

	/**
	 * Calcula determinada data em que o prazo irá zerar.
	 * 
	 * @param dtInicio
	 *            Data de inicio do evento
	 * @param nrDiasPrazo
	 *            Total de dias para determinado prazo expirar.
	 * @param consideraHora
	 *            Se o algoritmo deve considerar as horas no calculo.
	 * @param consideraMinuto
	 *            Se o algoritmo deve considerar os minutos no calculo.
	 * @param consideraSegundo
	 *            Se o algoritmo deve considerar os segundos no calculo.
	 * @param consideraMilisegundo
	 *            Se o algoritmo deve considerar os milisegundos no calculo.
	 * @return Data em que o prazo irá zerar.
	 */
	public static Calendar calculaDataPrazo(final Date dtInicio,
			final Integer nrDiasPrazo, final boolean consideraHora,
			final boolean consideraMinuto, final boolean consideraSegundo,
			final boolean consideraMilisegundo) {

		// Data ignorar
		final Calendar calendarIgnorar = Calendar.getInstance();
		calendarIgnorar.setTime(dtInicio);
		calendarIgnorar.add(Calendar.DAY_OF_MONTH, nrDiasPrazo);

		if (!consideraHora) {
			calendarIgnorar.set(Calendar.HOUR_OF_DAY, 0);
		}

		if (!consideraMinuto) {
			calendarIgnorar.set(Calendar.MINUTE, 0);
		}

		if (!consideraSegundo) {
			calendarIgnorar.set(Calendar.SECOND, 0);
		}

		if (!consideraMilisegundo) {
			calendarIgnorar.set(Calendar.MILLISECOND, 0);
		}

		return calendarIgnorar;
	}

	/**
	 * Calcula a diferenca entre duas datas em dias ignorando hora, minuto,
	 * segundo e milisegundos.
	 * 
	 * @param dtInicio
	 *            Data inicial
	 * @param dtFim
	 *            Data final
	 * @return Dias de diferenca entre as duas datas.
	 */
	public static int calculaDiferenca(final Date dtInicio, final Date dtFim) {

		final Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(dtInicio);
		calendarInicio.set(Calendar.HOUR_OF_DAY, 0);
		calendarInicio.set(Calendar.MINUTE, 0);
		calendarInicio.set(Calendar.SECOND, 0);
		calendarInicio.set(Calendar.MILLISECOND, 0);

		final Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(dtFim);
		calendarFim.set(Calendar.HOUR_OF_DAY, 0);
		calendarFim.set(Calendar.MINUTE, 0);
		calendarFim.set(Calendar.SECOND, 0);
		calendarFim.set(Calendar.MILLISECOND, 0);

		// Calcula a diferenca
		final long diferencaMilisegundos = calendarFim.getTimeInMillis()
				- calendarInicio.getTimeInMillis();
		double diferencaDias = diferencaMilisegundos / MILISEGUNDOS_DIA;

		return (int) diferencaDias;
	}

	/**
	 * Retorna a data atual.
	 */
	public static Date hoje() {
		return new Date();
	}

}
