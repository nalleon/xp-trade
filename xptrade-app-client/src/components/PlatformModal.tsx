import React from 'react';
import { Text, View, TouchableOpacity } from 'react-native';

const PlatformModal = ({ showModal, platforms, selectedPlatform, setSelectedPlatform, handlePlatformSelectionDone }) => {
  if (!showModal) return null;

  return (
    <View className="absolute top-0 left-0 right-0 bottom-0 bg-black/25 flex justify-center items-center z-50">
      <View className="bg-[#1E222A] p-6 rounded-xl w-11/12 max-w-md">
        <Text className="text-[#F6F7F7] text-lg font-semibold mb-4 text-center">
          Plataforma
        </Text>

        {platforms.map((p) => (
          <TouchableOpacity
            key={p.platform.id}
            onPress={() => {
              setSelectedPlatform((prev) =>
                prev.includes(p.platform.name)
                  ? prev.filter((n) => n !== p.platform.name)
                  : [...prev, p.platform.name]
              );
            }}
            className={`p-2 rounded mb-2 ${selectedPlatform.includes(p.platform.name)
              ? 'bg-[#556791]'
              : 'bg-[#2C3038]'
              }`}
          >
            <Text className="text-[#F6F7F7] text-center">{p.platform.name}</Text>
          </TouchableOpacity>
        ))}
        <TouchableOpacity
          onPress={handlePlatformSelectionDone}
          disabled={selectedPlatform.length === 0}
          className={`mt-4 p-2 rounded ${selectedPlatform.length === 0 ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
        >
          <Text className={`text-center font-bold ${selectedPlatform.length === 0 ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
            Confirmar
          </Text>
        </TouchableOpacity>

      </View>
    </View>
  );
};

export default PlatformModal;
