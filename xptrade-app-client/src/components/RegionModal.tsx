import React from 'react';
import { Text, View, TouchableOpacity } from 'react-native';

const RegionModal = ({ showModal, selectedRegions, setSelectedRegions, handleRegionSelectionDone }) => {
    if (!showModal) return null;

    return (
        <View className="absolute top-0 left-0 right-0 bottom-0 bg-black/25 flex justify-center items-center z-50">
            <View className="bg-[#1E222A] p-6 rounded-xl w-11/12 max-w-md">
                <Text className="text-[#F6F7F7] text-lg font-semibold mb-4 text-center">
                    Región
                </Text>

                {['EU', 'USA', 'ASIA', 'JP', 'GLOBAL'].map((region) => (
                    <TouchableOpacity
                        key={region}
                        onPress={() => {
                            setSelectedRegions((prev) =>
                                prev.includes(region)
                                    ? prev.filter((r) => r !== region)
                                    : [...prev, region]
                            );
                        }}
                        className={`p-2 rounded mb-2 ${selectedRegions.includes(region)
                            ? 'bg-[#556791]'
                            : 'bg-[#2C3038]'
                            }`}
                    >
                        <Text className="text-[#F6F7F7] text-center">{region}</Text>
                    </TouchableOpacity>
                ))}
                <TouchableOpacity
                    onPress={handleRegionSelectionDone}
                    disabled={selectedRegions.length === 0}
                    className={`mt-4 p-2 rounded ${selectedRegions.length === 0 ? 'bg-[#444]' : 'bg-[#9D8D6A]'}`}
                >
                    <Text className={`text-center font-bold ${selectedRegions.length === 0 ? 'text-[#0F1218]' : 'text-[#F6F7F7]'}`}>
                        Confirmar
                    </Text>
                </TouchableOpacity>

            </View>
        </View>
    );
};

export default RegionModal;
