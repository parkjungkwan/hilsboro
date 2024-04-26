package hillsboro.philoarte.scalar.providerImpls;

import hillsboro.philoarte.scalar.providers.ModelProvider;
import org.modelmapper.ModelMapper;

public class ModelProviderImpl implements ModelProvider {

    @Override
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
