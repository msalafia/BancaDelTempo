# BancaDelTempo
Progetto di Ingegneria del Software 2015

Developers: Damiano Di Stefano, Marco Giuseppe Salafia.

Questo è la realizzazione del progetto di Ingegeria del Software 2015 dell'omonimo corso tenuto dal prof. Orazio Tomarchio 
alla Laurea Magistrale di Ingegneria Informatica dell'Università degli studi di Catania. Lo scopo dell'elaborato era quello
di porgettare un software secondo un metodo RUP, sviluppando un numer sufficiente di casi d'uso e implementandoli in un 
software.

Il testo del nostro elaborato è il seguente:

Progetto - Banca del Tempo
Una BdT è una istituzione i cui cittadini membri (detti correntisti) possono prendere in
prestito ed offrire ore del loro tempo libero ad altri correntisti, per soddisfare i loro
bisogni reciproci. Lo scopo è quello di sviluppare forme di scambio e aiuto reciproco
instaurando un sistema di rapporti di buon vicinato e di relazioni sociali.
In breve, tutti i correntisti (ognuno dei quali è titolare di un “conto-corrente-tempo”
presso la Banca) dichiarano l’intenzione di volersi mettere a disposizione per un
certo insieme di prestazioni (ad es., “riparazioni domestiche”, “corsi di Inglese”, ecc.).
Queste vengono chiamate le abilità messe a disposizione dai singoli correntisti. Tali
abilità possono essere richieste dagli altri correntisti per risolvere delle loro necessità.
Si vuole sviluppare una applicazione che permetta l’incontro tra richieste ed offerte di
abilità.
In particolare, per l'applicazione sono di interesse i correntisti, con nome, cognome,
codice fiscale, indirizzo e telefono, le abilità messe a disposizione da ciascuno di essi
(di cui interessa il nome), e gli scambi di tempo che intercorrono tra coppie di
correntisti (non si preveda, per semplicità, la possibilità di scambi di tempo che
coinvolgono più di due correntisti). Degli scambi interessa la data e l’ora d’inizio, la
durata (in ore), e la particolare abilità richiesta al correntista offerente.
Il valore del tempo scambiato non è sempre lo stesso, dato che dipende dalla
tipologia del correntista offerente.
In particolare i correntisti si dividono in tre categorie distinte: lavoratori, nonlavoratori,
e pensionati. Dato uno scambio di tempo, il suo valore (ovvero l’importo
che il richiedente deve pagare all’offerente) si calcola come segue: se il correntista
offerente è un pensionato, tale valore è semplicemente pari alla durata in ore della
prestazione, mentre se è un lavoratore, è pari ad una volta e mezza tale durata. Per i
non-lavoratori invece, è pari ad 1.2 volte la durata in ore.
(Tale differenza vuole dare più valore al tempo offerto dai lavoratori, che hanno meno
tempo libero a disposizione.)
Al momento di inizio di uno scambio di tempo, il conto-corrente-tempo del correntista
offerente viene accreditato di un importo pari al valore dello scambio, mentre quello
del correntista richiedente viene addebitato dello stesso importo.
In ogni momento, per i conti-corrente-tempo dei correntisti si deve poter calcolare il
saldo, che è pari alla somma dei valori degli scambi offerti meno i valori di quelli
richiesti.
Si noti che, in ogni momento, il saldo di tutti i correntisti deve essere maggiore o
uguale a zero (cioè i correntisti non possono andare in “scoperto di conto”). Pertanto
un correntista non può richiedere uno scambio di tempo se si prevede che, al
momento di inizio di tale scambio, non abbia credito sufficiente a pagarlo. Tuttavia, al
fine di garantire il buon funzionamento del sistema nelle sue fasi iniziali, tutti i
correntisti ricevono, al momento della loro iscrizione, un accredito bonus di 10 ore sul
loro conto-corrente-tempo.
Allo scopo di introdurre dei meccanismi di controllo nel sistema ed evitare una cattiva
qualità dei servizi scambiati, ogni correntista è tenuto a dichiarare, per ogni abilità
che intende offrire, il grado della sua “competenza” nella misura di un intero che va
da 6 (sufficiente) a 10 (ottimo). Il sistema deve essere in grado di rappresentare tali
autovalutazioni. Tuttavia, per evitare autovalutazioni errate o addirittura mendaci,
vanno previsti opportuni meccanismi di controllo. A tal fine, si prevede che ad ogni
scambio il correntista richiedente esprima un giudizio da 1 a 10 sulla qualità del
servizio ricevuto. Il sistema deve quindi essere in grado di rappresentare anche tali
giudizi (detti feedback).
