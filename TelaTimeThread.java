package cursojava.theads;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TelaTimeThread extends JDialog{

	private JPanel jpanel = new JPanel(new GridBagLayout());/*Painel de componentes*/
	private JLabel descricaoHoraBr = new JLabel("Time Brasil");
	private JTextField mostraTempoBr = new JTextField();
	
	private JLabel descricaoHoraKr = new JLabel("Time Coreia");
	private JTextField mostraTempoKr = new JTextField();
	
	private JButton bStart = new JButton("Start");
	private JButton bStop = new JButton("Stop");
	
	private Thread threadTimeBr;
	private Thread threadTimeKr;
	
	private boolean flagRun = true;
	public TelaTimeThread() {
		/*CONFIGURAÇÕES INICIAIS DA TELA*/
		setTitle("Tela de time com threads");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		/*MONTAGEM DA TELA*/
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		/*===========================================LABELS E TEXT FIELDS===========================================*/
		
		descricaoHoraBr.setPreferredSize(new Dimension(200,25));
		jpanel.add(descricaoHoraBr,gridBagConstraints);
		
		mostraTempoBr.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy++;
		jpanel.add(mostraTempoBr,gridBagConstraints);
		mostraTempoBr.setEditable(false);
		
		descricaoHoraKr.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy++;
		jpanel.add(descricaoHoraKr,gridBagConstraints);
		
		mostraTempoKr.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy++;
		jpanel.add(mostraTempoKr,gridBagConstraints);
		mostraTempoKr.setEditable(false);
		
		/*===========================================BUTTONS===========================================*/
		
		gridBagConstraints.gridwidth = 1;
		bStart.setPreferredSize(new Dimension(92,25));
		gridBagConstraints.gridy++;
		jpanel.add(bStart,gridBagConstraints);
		
		bStop.setPreferredSize(new Dimension(92,25));
		gridBagConstraints.gridx++;
		jpanel.add(bStop,gridBagConstraints);
		
		bStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flagRun = true;
				threadTimeBr = new Thread(threadBr);
				threadTimeBr.start();
				threadTimeKr = new Thread(threadKr);
				threadTimeKr.start();
				bStart.setEnabled(false);
				bStop.setEnabled(true);
			}
		});
		
		bStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flagRun = false;
				bStart.setEnabled(true);
				bStop.setEnabled(false);
			}
		});
		bStop.setEnabled(false);
		add(jpanel,BorderLayout.WEST);
		setVisible(true);
	}
	
	private Runnable threadBr = new Runnable() {
		
		@Override
		public void run() {
			while (flagRun) {
				mostraTempoBr.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm.ss").
						format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
			}
		}
	};
	
	
private Runnable threadKr = new Runnable() {
		
		@Override
		public void run() {
			while (flagRun) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
				mostraTempoKr.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").
						format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	};
	
}
