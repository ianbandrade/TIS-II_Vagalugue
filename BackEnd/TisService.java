import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class TisService {

    private final static int NUM_VAGAS_MAX = 100;
    static Vaga[] vagas = new Vaga[ NUM_VAGAS_MAX ];
    public static int count_vagas;

    public JSONObject adicionar(Request request) {
        String nome;
        String sobrenome;

        String indicador;
        String foto;
        String descricao;

        double largura;
        double comprimento;
        double altura;

        String cep;
        String endereco;
        int numero;
        String bairro;
        String cidade;
        String estado;

        Vaga v = null;

        Query query = request.getQuery();

        nome = query.get("nome");
        sobrenome = query.get("sobrenome");
        indicador = query.get("indicador_vaga");
        foto = query.get("foto_vaga");
        descricao = query.get("descricao");
        largura = query.getFloat("largura");
        comprimento = query.getFloat("comprimento");
        altura = query.getFloat("altura");
        cep = query.get("cep");
        endereco = query.get("endereco");
        numero = query.getInteger("numero");
        bairro = query.get("bairro");
        cidade = query.get("cidade");
        estado = query.get("estado");

        v = new Vaga(new Locatario(nome, sobrenome), indicador, foto, descricao,
                new Dimensoes(largura, comprimento, altura),
                new Localizacao(cep, endereco, numero, bairro, cidade, estado));

        vagas[ count_vagas++ ] = v;

        return toJson(v);
    }

    public JSONObject listar() {
        return arrayToJson(vagas);
    }

    private static JSONObject toJson(Vaga v) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("Locatario", v.getLocatarioJson());
        json.put("Indicador", v.getIndicador());
        json.put("Foto", v.getFoto());
        json.put("Descricao", v.getDescricao());
        json.put("Dimensoes", v.getDimensoesJson());
        json.put("Localizacao", v.getLocalizacaoJson());
        return json;
    }

    private static JSONObject arrayToJson(Vaga[] vagas) throws JSONException {
        JSONObject jsonO = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonO.put("vagas", jsonArray);
        Vaga v;
        int i = 0;
        while (vagas[ i ] != null) {
            v = vagas[ i ];
            JSONObject json = new JSONObject();
            json.put("ID", i);
            json.put("Locatario", v.getLocatarioJson());
            json.put("Indicador", v.getIndicador());
            json.put("Foto", v.getFoto());
            json.put("Descricao", v.getDescricao());
            json.put("Dimensoes", v.getDimensoesJson());
            json.put("Localizacao", v.getLocalizacaoJson());
            jsonArray.put(json);
            i++;
        }
        return jsonO;
    }
}
