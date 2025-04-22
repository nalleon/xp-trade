import React, { useState, useRef } from 'react';
import {
  View,
  ScrollView,
  Modal,
  TouchableOpacity,
  Text,
  Image,
  FlatList,
  Dimensions,
  ActivityIndicator,
} from 'react-native';
import { ImageZoom } from '@likashefqet/react-native-image-zoom';
import Icon from 'react-native-vector-icons/Ionicons';

const { width, height } = Dimensions.get('window');

const ScreenshotGallery = ({ screenshots }) => {
  const [visible, setVisible] = useState(false);
  const [activeIndex, setActiveIndex] = useState(0);
  const flatListRef = useRef(null);
  const [loading, setLoading] = useState(true);

  const openModal = (index) => {
    setActiveIndex(index);
    setVisible(true);
    setLoading(true);
  };

  const closeModal = () => {
    setVisible(false);
  };

  const handleFlatListLayout = () => {
    setTimeout(() => {
      flatListRef.current?.scrollToIndex({ index: activeIndex, animated: true });
    }, 50);
  };

  const handleImageLoad = () => {
    setLoading(false); 
  };

  return (
    <View className="mb-8">
      <Text className="text-[#F6F7F7] font-semibold mb-2">Otras imágenes:</Text>

      <ScrollView horizontal showsHorizontalScrollIndicator={false}>
        {screenshots.map((screenshot, index) => (
          <TouchableOpacity
            key={screenshot.id}
            onPress={() => openModal(index)}
            className="mr-3"
          >
            <Image
              source={{ uri: screenshot.image }}
              className="w-40 h-24 rounded-lg"
              resizeMode="cover"
            />
          </TouchableOpacity>
        ))}
      </ScrollView>

      <Modal visible={visible} transparent={true} onRequestClose={closeModal}>
        <View className="flex-1 bg-[#0F1218]">
          <FlatList
            ref={flatListRef}
            data={screenshots}
            keyExtractor={(item) => item.id.toString()}
            horizontal
            pagingEnabled
            onLayout={handleFlatListLayout}
            getItemLayout={(data, index) => ({
              length: width,
              offset: width * index,
              index,
            })}
            renderItem={({ item, index }) => (
              <View style={{ width, height, justifyContent: 'center', alignItems: 'center' }}>
                {loading && (
                  <ActivityIndicator size="large" color="#fff" style={{ position: 'absolute' }} />
                )}
                    <ImageZoom
                        key={index}
                        minScale={1}
                        maxScale={200}
                        style={{ width, height }}
                        doubleTapScale={90}
                        isPinchEnabled={true}
                        isPanEnabled={true}
                        isDoubleTapEnabled={true}
                        onLoad={handleImageLoad}
                        resizeMode="contain"
                        source={{ uri: item.image }}
                    />
              </View>
            )}
          />

          <TouchableOpacity
            onPress={closeModal}
            className="absolute top-10 right-1 px-4 py-2 rounded-full"
          >
            <Icon name="close" size={35} color="#556791" />
          </TouchableOpacity>
        </View>
      </Modal>
    </View>
  );
};

export default ScreenshotGallery;
