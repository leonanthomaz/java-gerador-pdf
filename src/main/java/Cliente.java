import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {
    private Long id;
    private String nome;
    private Integer idade;
    private String email;
}
