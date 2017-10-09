package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.HorarioSalaDTO;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface HorariosDao {
    
    List<HorarioSalaDTO> listarHorarioSala(String sala);
    
}
