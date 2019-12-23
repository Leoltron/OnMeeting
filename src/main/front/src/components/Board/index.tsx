import React, {useEffect, useState} from "react";
import "./Board.less"
import {useHistory} from "react-router-dom";
import {deleteCard, getAllUsers, getParticipatingCards, getPrincipal} from "../../httpClient";
import report from "../../utils/report";
import Logout from "../LogoutButton";
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';
import {CardViewModel} from "../../models/CardViewModel";
import useDocumentTitle from "../../utils/useDocumentTitle";
import CardModal from "./CardModal";
import Card from "../Card";
import {UserModel} from "../../models/userModel";

const Board: React.FC = () => {
    useDocumentTitle("Board — OnMeeting");
    const [username, setUsername] = useState("guest");
    const history = useHistory();
    const [cards, setCards] = useState([] as CardViewModel[]);
    const [openModal, setOpenModal] = useState(false);
    const [openedCard, setOpenedCard] = useState<CardViewModel>();
    const [allUsers, setAllUsers] = useState([] as [] as UserModel[]);
    const checkUserAuthorized = () => {
        getPrincipal()
            .then(p => {
                if (!p.authorized) {
                    history.push("/")
                }
                else {
                    setUsername(p.username)
                }
            })
            .catch(report);
    };
    const onModalCLose = () => {
        setOpenedCard(undefined);
        setOpenModal(false);
    };
    const onAddCardModalOpen = () => {
        setOpenedCard(undefined);
        setOpenModal(true);
    };
    const onEditCardModalOpen = (card: CardViewModel) => {
        setOpenedCard(card);
        setOpenModal(true);
    };
    const loadCards = () => {
        checkUserAuthorized();
        getParticipatingCards().then(setCards).catch(report)
    };
    const onDeleteCard = (cardId: number) => {
        deleteCard(cardId).then(loadCards).catch(report)
    };

    useEffect(() => {
        loadCards();
        const intervalID = window.setInterval(loadCards, 5000);
        getAllUsers()
            .then(setAllUsers)
            .catch(report);
        return () => {
            window.clearInterval(intervalID);
        }
    //eslint-disable-next-line
    }, []);

    return (
        <>
            <div className="Board-container">
                <BoardHeader username={username} onAddCardModalOpen={cards.length !== 0 ? onAddCardModalOpen : undefined} />
                <div className="Board-wrapper">
                    {cards.length === 0
                        ? <EmptyBoard onAddCardModalOpen={onAddCardModalOpen} />
                        : <NotEmptyBoard boardOwner={username} cards={cards} onDelete={onDeleteCard} onEdit={onEditCardModalOpen}/>
                    }
                </div>
            </div>
            {openModal ? <CardModal allUsers={allUsers.filter(u => u.name !== username)} isOpen={openModal} close={onModalCLose} card={openedCard}/> : null}
        </>
    );
};

const BoardHeader: React.FC<{username: string, onAddCardModalOpen?: () => void}> = ({username, onAddCardModalOpen}) => (
    <div className="Board-header">
        <h4 className="Board-title">{`${username}'s board`}</h4>
        {onAddCardModalOpen
            ? <AddCardButton onCLick={onAddCardModalOpen}/>
            : null}
        <Logout />
    </div>
);

const EmptyBoard: React.FC<{onAddCardModalOpen: () => void}> = ({onAddCardModalOpen}) => (
    <div className="EmptyBoard">
        <span>You don't have any cards yet...</span>
        <AddCardButton onCLick={onAddCardModalOpen}/>
    </div>
);

interface BoardProps {
    boardOwner: string
    cards: CardViewModel[]
    onDelete: (cardId: number) => void
    onEdit: (card: CardViewModel) => void
}
const NotEmptyBoard: React.FC<BoardProps> = ({boardOwner, cards, onDelete, onEdit}) => {
    return (
        <div className="Board-grid">
            { cards.map(c => <Card key={c.cardId} onDelete={onDelete} onEdit={onEdit} boardOwner={boardOwner} {...c} />) }
        </div>
    )
};

const AddCardButton: React.FC<{onCLick: () => void}> = ({onCLick}) => (
    <Button
        variant="contained"
        color="default"
        startIcon={<AddIcon />}
        children="Add"
        onClick={onCLick}
    />
);

export default Board;