package com.project.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;
import com.project.service.EksportService;
import com.project.service.LoginService;

import au.com.bytecode.opencsv.CSVWriter;

public class EksportCSV {

	@Autowired
	private EksportService eksportService;

	public void setEksportService(EksportService eksportService) {
		this.eksportService = eksportService;
	}

	public void do_eksportcsv(int idGrupy) throws SQLException {
		String csv = "C:\\Users\\Przemo\\Desktop\\ZapisCSV";
		String infoPwr, rokAka, typKal, sem, kodGrup, kodKursu, nazwaKurs, termin, prowadzacy;
		List<GrupyZajeciowe> listGroup = eksportService.getGroupsZaj(idGrupy);
		String infoEdu = listGroup.get(0).getInfoEdu();
		// String[] field = infoEdu.split(";",-1);
		Date daty = new Date();
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(csv));
			// String rok_akademicki = null;
			writer.equals(StandardCharsets.US_ASCII);
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "\n" });
			StringTokenizer stringTokenizer = new StringTokenizer(infoEdu,
					";  ");
			while (stringTokenizer.hasMoreTokens()) {

				infoPwr = stringTokenizer.nextToken(" ");

				rokAka = stringTokenizer.nextToken();
				typKal = stringTokenizer.nextToken();
				sem = stringTokenizer.nextToken();
				kodGrup = stringTokenizer.nextToken();
				kodKursu = stringTokenizer.nextToken();
				nazwaKurs = stringTokenizer.nextToken();
				termin = stringTokenizer.nextToken();
				prowadzacy = stringTokenizer.nextToken();

				// data.add(new String[] {infoEdu});
				data.add(new String[] { infoPwr });
				data.add(new String[] { rokAka });
				data.add(new String[] { typKal });
				data.add(new String[] { sem });
				data.add(new String[] { kodGrup });
				data.add(new String[] { kodKursu });
				data.add(new String[] { nazwaKurs });
				data.add(new String[] { termin });
				data.add(new String[] { prowadzacy });
			}
			data.add(new String[] { "Lp.;", "Nr albumu;", "Nazwisko;",
					"Imiona;", "Nazwisko;", "Rok;", "Semestr;",
					"Przedmiot kształcenia", "Ocena (np. 3.0);",
					"Data (RRRR-MM-DD);", "Komentarz" });
			/*
			 * Tutaj petla studentow dodanych do bazy , Musimy posortowac po
			 * nazwisku i imieniu
			 */
			List<StudenciDoGrupZajeciowych> studZaj = eksportService
					.getStudZaj(idGrupy);
			int groupChodz = 0;
			int idGrupki = 0;
			int idStudenta = 0;
			String wartoscOcenki = null;
			int studencik = 0;
			for (int i = 0; i < studZaj.size(); i++) {
				groupChodz = studZaj.get(i).getIdGrupyChodzacej()
						.getIdGrupyZajeciowe();
				idStudenta = studZaj.get(i).getIdStudenta().getIdStudenci();
				List<GrupyProjektowe> grupProj = eksportService
						.getGroupsProj(groupChodz);
				for (int j = 0; j < grupProj.size(); j++) {
					idGrupki = grupProj.get(j).getIdGrupyProjektowe();

					List<StudenciDoGrupProjektowych> zajecia = eksportService
							.studProj(idStudenta, idGrupki);
					if (zajecia.size() > 0) {
						for (StudenciDoGrupProjektowych sdgp : zajecia) {
							wartoscOcenki = sdgp.getOcena();
							studencik = sdgp.getIdStudenta().getIdStudenci();
							List<Studenci> moi = eksportService
									.getStudents(studencik);
							int a = 1;
							String licznik = Integer.toString(a);
							data.add(new String[] {
									licznik + ";",
									moi.get(0).getNrIndeksu() + ";",
									moi.get(0).getNazwisko() + ";",
									moi.get(0).getImie() + ";",
									moi.get(0).getRok().toString() + ";",
									moi.get(0).getSemestr().toString() + ";",
									moi.get(0).getPrzedmiot_ksztalcenia() + ";",
									wartoscOcenki + ";",
									daty.toString() + ";" + ";" });

							a++;
						}
					}
				}
			}
			System.out
					.println("I oto naszym oczom i uszą ukazuję się, bądź też nie wygląd prawdopodobnie całej csvki wyeksportowanej");
			System.out
					.println("Gdy pojawią się jakieś wielkie, wręcz duże problemy proszę zgłoś błąd pod adres mail grupy.pwr.wroc@gmail.com");
			System.out.println("Pozdrawia cierpiący na bezsenność admin ;P");
			System.out.println(data);
			// Collections.sort();
			data.add(new String[] {});
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
