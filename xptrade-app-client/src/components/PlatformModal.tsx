import React from 'react';
import { Text, View, TouchableOpacity } from 'react-native';

const PlatformModal = ({ showModal, platforms, selectedPlatforms, setSelectedPlatforms, handlePlatformSelectionDone }) => {
  if (!showModal) return null;

  return (
    <View className="absolute top-0 left-0 right-0 bottom-0 bg-black/25 flex justify-center items-center z-50">
      <View className="bg-[#1E222A] p-6 rounded-xl w-11/12 max-w-md">
        <Text className="text-[#F6F7F7] text-lg font-semibold mb-4 text-center">
          Selecciona plataformas
        </Text>

        {platforms.map((p) => (
          <TouchableOpacity
            key={p.platform.id}
            onPress={() => {
              setSelectedPlatforms((prev) =>
                prev.includes(p.platform.name)
                  ? prev.filter((n) => n !== p.platform.name)
                  : [...prev, p.platform.name]
              );
            }}
            className={`p-2 rounded mb-2 ${selectedPlatforms.includes(p.platform.name)
              ? 'bg-[#66B3B7]'
              : 'bg-[#2C3038]'
              }`}
          >
            <Text className="text-white text-center">{p.platform.name}</Text>
          </TouchableOpacity>
        ))}

        <TouchableOpacity
          onPress={handlePlatformSelectionDone}
          className="mt-4 p-2 bg-[#66B3B7] rounded"
        >
          <Text className="text-center text-[#0F1218] font-bold">Siguiente</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default PlatformModal;
